package com.github.franckyi.ibeeditor.forge;

import com.github.franckyi.ibeeditor.base.common.NetworkManager;
import com.github.franckyi.ibeeditor.base.common.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.PacketDistributor;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

import java.nio.file.Path;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class PlatformUtilImpl {
    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    private static final String VERSION = "2";
    private static final SimpleChannel channel = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("ibeeditor:network"),
            () -> VERSION,
            VERSION::equals,
            VERSION::equals
    );

    public static void sendToServer(String id, Packet packet) {
        channel.sendToServer(packet);
    }

    public static void sendToClient(String id, ServerPlayer player, Packet packet) {
        channel.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static <P extends Packet> void registerServerHandler(String id, int id1, Class<P> msgClass, NetworkManager.PacketReader<P> reader, NetworkManager.ServerPacketHandler<P> handler) {
        registerHandler(id1, msgClass, reader, (msg, ctx) -> handler.accept(msg, ctx.get().getSender()));
    }

    public static <P extends Packet> void registerClientHandler(String id, int id1, Class<P> msgClass, NetworkManager.PacketReader<P> reader, NetworkManager.ClientPacketHandler<P> handler) {
        registerHandler(id1, msgClass, reader, (msg, ctx) -> handler.accept(msg));
    }

    private static <P extends Packet> void registerHandler(int id, Class<P> msgClass, NetworkManager.PacketReader<P> reader, BiConsumer<P, Supplier<NetworkEvent.Context>> handler) {
        channel.messageBuilder(msgClass, id)
                .decoder(buffer -> {
                    try {
                        return reader.read(buffer);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .encoder((p, buffer) -> {
                    try {
                        p.write(buffer);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .consumer((msg, ctx) -> {
                    ctx.get().enqueueWork(() -> handler.accept(msg, ctx));
                    ctx.get().setPacketHandled(true);
                }).add();
    }
}
