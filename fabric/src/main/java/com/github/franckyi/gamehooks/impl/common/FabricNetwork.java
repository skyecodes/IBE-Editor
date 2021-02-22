package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.network.Buffer;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.api.common.network.Packet;
import com.github.franckyi.gamehooks.util.common.network.ClientPacketHandler;
import com.github.franckyi.gamehooks.util.common.network.ServerPacketHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public final class FabricNetwork implements Network<ServerPlayerEntity> {
    public static final Network<ServerPlayerEntity> INSTANCE = new FabricNetwork();

    private FabricNetwork() {
    }

    @Override
    public void sendToServer(String id, Packet packet) {
        FabricBuffer buf = new FabricBuffer();
        packet.write(buf);
        ClientPlayNetworking.send(new Identifier(id), buf.getBuffer());
    }

    @Override
    public void sendToClient(String id, ServerPlayerEntity entity, Packet packet) {
        FabricBuffer buf = new FabricBuffer();
        packet.write(buf);
        ServerPlayNetworking.send(entity, new Identifier(id), buf.getBuffer());
    }

    @Override
    public <P extends Packet> void registerServerHandler(String id, int id1, Class<P> msgClass, Function<Buffer<?>, P> reader, ServerPacketHandler<P> handler) {
        ServerPlayNetworking.registerGlobalReceiver(new Identifier(id), (server, entity, networkHandler, buf, sender) -> {
            P packet = reader.apply(new FabricBuffer(buf));
            server.execute(() -> handler.accept(packet, new FabricServerPlayer(entity)));
        });
    }

    @Override
    public <P extends Packet> void registerClientHandler(String id, int id1, Class<P> msgClass, Function<Buffer<?>, P> reader, ClientPacketHandler<P> handler) {
        ClientPlayNetworking.registerGlobalReceiver(new Identifier(id), (client, networkHandler, buf, sender) -> {
            P packet = reader.apply(new FabricBuffer(buf));
            client.execute(() -> handler.accept(packet));
        });
    }
}
