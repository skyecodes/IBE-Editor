package com.github.franckyi.ibeeditor.fabric;

import com.github.franckyi.ibeeditor.common.ClientPacketHandler;
import com.github.franckyi.ibeeditor.common.Packet;
import com.github.franckyi.ibeeditor.common.PacketReader;
import com.github.franckyi.ibeeditor.common.ServerPacketHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.nio.file.Path;

public class PlatformUtilImpl {
    public static Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static void sendToServer(String id, Packet packet) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        try {
            packet.write(buf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ClientPlayNetworking.send(new ResourceLocation(id), buf);
    }

    public static void sendToClient(String id, ServerPlayer player, Packet packet) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        try {
            packet.write(buf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ServerPlayNetworking.send(player, new ResourceLocation(id), buf);
    }

    public static <P extends Packet> void registerServerHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ServerPacketHandler<P> handler) {
        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(id), (server, entity, networkHandler, buf, sender) -> {
            try {
                P packet = reader.read(buf);
                server.execute(() -> handler.accept(packet, entity));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static <P extends Packet> void registerClientHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ClientPacketHandler<P> handler) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(id), (client, networkHandler, buf, sender) -> {
                try {
                    P packet = reader.read(buf);
                    client.execute(() -> handler.accept(packet));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
