package com.github.franckyi.ibeeditor.fabric;

import com.github.franckyi.ibeeditor.common.network.ClientNetworkHandler;
import com.github.franckyi.ibeeditor.common.network.ServerNetworkHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.level.ServerPlayer;

import java.nio.file.Path;

public class PlatformUtilImpl {
    public static Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static <P> void sendToServer(ServerNetworkHandler<P> handler, P packet) {
        var buf = PacketByteBufs.create();
        handler.getSerializer().write(packet, buf);
        ClientPlayNetworking.send(handler.getLocation(), buf);
    }

    public static <P> void sendToClient(ClientNetworkHandler<P> handler, ServerPlayer player, P packet) {
        var buf = PacketByteBufs.create();
        handler.getSerializer().write(packet, buf);
        ServerPlayNetworking.send(player, handler.getLocation(), buf);
    }

    public static <P> void registerServerHandler(ServerNetworkHandler<P> handler) {
        ServerPlayNetworking.registerGlobalReceiver(handler.getLocation(), (server, entity, networkHandler, buf, sender) ->
                server.execute(() -> handler.getPacketHandler().handle(handler.getSerializer().read(buf), entity)));
    }

    public static <P> void registerClientHandler(ClientNetworkHandler<P> handler) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ClientPlayNetworking.registerGlobalReceiver(handler.getLocation(), (client, networkHandler, buf, responseSender) ->
                    client.execute(() -> handler.getPacketHandler().handle(handler.getSerializer().read(buf))));
        }
    }
}
