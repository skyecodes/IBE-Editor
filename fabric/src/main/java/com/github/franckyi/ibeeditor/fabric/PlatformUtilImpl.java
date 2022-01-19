package com.github.franckyi.ibeeditor.fabric;

import com.github.franckyi.ibeeditor.common.network.NetworkHandler;
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

    public static <P> void sendToServer(NetworkHandler.Server<P> handler, P packet) {
        var buf = PacketByteBufs.create();
        handler.getSerializer().write(packet, buf);
        ClientPlayNetworking.send(handler.getLocation(), buf);
    }

    public static <P> void sendToClient(ServerPlayer player, NetworkHandler.Client<P> handler, P packet) {
        var buf = PacketByteBufs.create();
        handler.getSerializer().write(packet, buf);
        ServerPlayNetworking.send(player, handler.getLocation(), buf);
    }

    public static <P> void registerServerHandler(NetworkHandler.Server<P> handler) {
        ServerPlayNetworking.registerGlobalReceiver(handler.getLocation(), (server, entity, networkHandler, buf, sender) -> {
            P packet = handler.getSerializer().read(buf);
            server.execute(() -> handler.getPacketHandler().handle(entity, packet));
        });
    }

    public static <P> void registerClientHandler(NetworkHandler.Client<P> handler) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ClientPlayNetworking.registerGlobalReceiver(handler.getLocation(), (client, networkHandler, buf, responseSender) -> {
                P packet = handler.getSerializer().read(buf);
                client.execute(() -> handler.getPacketHandler().handle(packet));
            });
        }
    }
}
