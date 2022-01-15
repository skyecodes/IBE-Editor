package com.github.franckyi.ibeeditor.forge;

import com.github.franckyi.ibeeditor.common.network.ClientNetworkHandler;
import com.github.franckyi.ibeeditor.common.network.NetworkHandler;
import com.github.franckyi.ibeeditor.common.network.ServerNetworkHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.nio.file.Path;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class PlatformUtilImpl {
    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    private static final String VERSION = "3";
    private static final SimpleChannel channel = NetworkRegistry.newSimpleChannel(new ResourceLocation("ibeeditor:network"), () -> VERSION, s -> true, s -> true);

    public static <P> void sendToServer(ServerNetworkHandler<P> handler, P packet) {
        channel.sendToServer(packet);
    }

    public static <P> void sendToClient(ClientNetworkHandler<P> handler, ServerPlayer player, P packet) {
        channel.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static <P> void registerServerHandler(ServerNetworkHandler<P> handler) {
        registerHandler(handler, (msg, ctx) -> handler.getPacketHandler().handle(msg, ctx.get().getSender()));
    }

    public static <P> void registerClientHandler(ClientNetworkHandler<P> handler) {
        registerHandler(handler, (msg, ctx) -> handler.getPacketHandler().handle(msg));
    }

    private static <P> void registerHandler(NetworkHandler<P> handler, BiConsumer<P, Supplier<NetworkEvent.Context>> action) {
        channel.messageBuilder(handler.getType(), handler.getId())
                .decoder(buffer -> handler.getSerializer().read(buffer))
                .encoder((p, buffer) -> handler.getSerializer().write(p, buffer))
                .consumer((msg, ctx) -> {
                    ctx.get().enqueueWork(() -> action.accept(msg, ctx));
                    ctx.get().setPacketHandled(true);
                }).add();
    }
}
