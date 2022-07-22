package com.github.franckyi.ibeeditor.forge;

import com.github.franckyi.ibeeditor.common.network.NetworkHandler;
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

    public static <P> void sendToServer(NetworkHandler.Server<P> handler, P packet) {
        channel.sendToServer(packet);
    }

    public static <P> void sendToClient(ServerPlayer player, NetworkHandler.Client<P> handler, P packet) {
        channel.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static <P> void registerServerHandler(NetworkHandler.Server<P> handler) {
        registerHandler(handler, (msg, ctx) -> handler.getPacketHandler().handle(ctx.get().getSender(), msg));
    }

    public static <P> void registerClientHandler(NetworkHandler.Client<P> handler) {
        registerHandler(handler, (msg, ctx) -> handler.getPacketHandler().handle(msg));
    }

    private static <P> void registerHandler(NetworkHandler<P> handler, BiConsumer<P, Supplier<NetworkEvent.Context>> action) {
        channel.messageBuilder(handler.getType(), handler.getId())
                .decoder(buffer -> handler.getSerializer().read(buffer))
                .encoder((p, buffer) -> handler.getSerializer().write(p, buffer))
                .consumerMainThread(action)
                .add();
    }
}
