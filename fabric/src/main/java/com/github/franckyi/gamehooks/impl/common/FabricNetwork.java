package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.api.common.network.Packet;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public final class FabricNetwork implements Network {
    public static final Network INSTANCE = new FabricNetwork();

    private FabricNetwork() {
    }

    @Override
    public void sendToServer(String id, Packet packet) {
        FabricBuffer buf = new FabricBuffer();
        packet.write(buf);
        ClientPlayNetworking.send(new Identifier(id), buf.getBuffer());
    }

    @Override
    public void sendToClient(String id, Player player, Packet packet) {
        FabricBuffer buf = new FabricBuffer();
        packet.write(buf);
        ServerPlayNetworking.send(player.get(), new Identifier(id), buf.getBuffer());
    }

    @Override
    public <P extends Packet> void registerServerHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ServerPacketHandler<P> handler) {
        ServerPlayNetworking.registerGlobalReceiver(new Identifier(id), (server, entity, networkHandler, buf, sender) -> {
            P packet = reader.read(new FabricBuffer(buf));
            server.execute(() -> handler.accept(packet, new FabricPlayer(entity)));
        });
    }

    @Override
    public <P extends Packet> void registerClientHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ClientPacketHandler<P> handler) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ClientPlayNetworking.registerGlobalReceiver(new Identifier(id), (client, networkHandler, buf, sender) -> {
                P packet = reader.read(new FabricBuffer(buf));
                client.execute(() -> handler.accept(packet));
            });
        }
    }
}
