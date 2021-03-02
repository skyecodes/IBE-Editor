package com.github.franckyi.gamehooks.api.common.network;

import com.github.franckyi.gamehooks.api.common.Player;

public interface Network {
    void sendToServer(String id, Packet packet);

    void sendToClient(String id, Player player, Packet packet);

    <P extends Packet> void registerServerHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ServerPacketHandler<P> handler);

    <P extends Packet> void registerClientHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ClientPacketHandler<P> handler);

    @FunctionalInterface
    interface PacketReader<P> {
        P read(Buffer buffer);
    }

    @FunctionalInterface
    interface ClientPacketHandler<P> {
        void accept(P packet);
    }

    @FunctionalInterface
    interface ServerPacketHandler<P> {
        void accept(P packet, Player sender);
    }
}
