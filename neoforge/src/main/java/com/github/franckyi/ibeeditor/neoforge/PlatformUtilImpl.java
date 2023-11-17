package com.github.franckyi.ibeeditor.neoforge;

import com.github.franckyi.ibeeditor.common.network.NetworkHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.network.NetworkRegistry;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.simple.MessageFunctions;
import net.neoforged.neoforge.network.simple.SimpleChannel;

import java.nio.file.Path;

public class PlatformUtilImpl {
    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    private static final String VERSION = "3";
    private static final SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation("ibeeditor:network"))
            .networkProtocolVersion(() -> VERSION)
            .clientAcceptedVersions(NetworkRegistry.acceptMissingOr(VERSION))
            .serverAcceptedVersions(NetworkRegistry.acceptMissingOr(VERSION))
            .simpleChannel();

    public static <P> void sendToServer(NetworkHandler.Server<P> handler, P packet) {
        channel.send(PacketDistributor.SERVER.noArg(), packet);
    }

    public static <P> void sendToClient(ServerPlayer player, NetworkHandler.Client<P> handler, P packet) {
        channel.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static <P> void registerServerHandler(NetworkHandler.Server<P> handler) {
        registerHandler(handler, (msg, ctx) -> handler.getPacketHandler().handle(ctx.getSender(), msg));
    }

    public static <P> void registerClientHandler(NetworkHandler.Client<P> handler) {
        registerHandler(handler, (msg, ctx) -> handler.getPacketHandler().handle(msg));
    }

    private static <P> void registerHandler(NetworkHandler<P> handler, MessageFunctions.MessageConsumer<P> action) {
        channel.messageBuilder(handler.getType(), handler.getId())
                .decoder(buffer -> handler.getSerializer().read(buffer))
                .encoder((p, buffer) -> handler.getSerializer().write(p, buffer))
                .consumerMainThread(action)
                .add();
    }
}
