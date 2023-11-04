package com.github.franckyi.ibeeditor.forge;

import com.github.franckyi.ibeeditor.common.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

import java.nio.file.Path;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class PlatformUtilImpl {
    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    private static final int VERSION = 3;
    private static final SimpleChannel channel = ChannelBuilder.named(new ResourceLocation("ibeeditor:network"))
            .networkProtocolVersion(VERSION)
            .optional()
            .simpleChannel();

    public static <P> void sendToServer(NetworkHandler.Server<P> handler, P packet) {
        channel.send(packet, PacketDistributor.SERVER.noArg());
    }

    public static <P> void sendToClient(ServerPlayer player, NetworkHandler.Client<P> handler, P packet) {
        channel.send(packet, PacketDistributor.PLAYER.with(player));
    }

    public static <P> void registerServerHandler(NetworkHandler.Server<P> handler) {
        registerHandler(handler, (msg, ctx) -> handler.getPacketHandler().handle(ctx.getSender(), msg));
    }

    public static <P> void registerClientHandler(NetworkHandler.Client<P> handler) {
        registerHandler(handler, (msg, ctx) -> handler.getPacketHandler().handle(msg));
    }

    private static <P> void registerHandler(NetworkHandler<P> handler, BiConsumer<P, CustomPayloadEvent.Context> action) {
        channel.messageBuilder(handler.getType(), handler.getId())
                .decoder(buffer -> handler.getSerializer().read(buffer))
                .encoder((p, buffer) -> handler.getSerializer().write(p, buffer))
                .consumerMainThread(action)
                .add();
    }
}
