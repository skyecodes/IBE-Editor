package com.github.franckyi.ibeeditor.fabric.common;

import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.ibeeditor.base.common.Network;
import com.github.franckyi.ibeeditor.base.common.Packet;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public final class FabricNetwork implements Network {
    public static final Network INSTANCE = new FabricNetwork();

    private FabricNetwork() {
    }

    @Override
    public void sendToServer(String id, Packet packet) {
        PacketByteBuf buf = PacketByteBufs.create();
        packet.write((IPacketBuffer) buf);
        ClientPlayNetworking.send(new Identifier(id), buf);
    }

    @Override
    public void sendToClient(String id, IPlayer player, Packet packet) {
        PacketByteBuf buf = PacketByteBufs.create();
        packet.write((IPacketBuffer) buf);
        ServerPlayNetworking.send((ServerPlayerEntity) player, new Identifier(id), buf);
    }

    @Override
    public <P extends Packet> void registerServerHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ServerPacketHandler<P> handler) {
        ServerPlayNetworking.registerGlobalReceiver(new Identifier(id), (server, entity, networkHandler, buf, sender) -> {
            P packet = reader.read((IPacketBuffer) buf);
            server.execute(() -> handler.accept(packet, (IPlayer) entity));
        });
    }

    @Override
    public <P extends Packet> void registerClientHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ClientPacketHandler<P> handler) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ClientPlayNetworking.registerGlobalReceiver(new Identifier(id), (client, networkHandler, buf, sender) -> {
                P packet = reader.read((IPacketBuffer) buf);
                client.execute(() -> handler.accept(packet));
            });
        }
    }
}
