package com.github.franckyi.ibeeditor.base.common;

import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.gameadapter.api.common.IPlayer;

public interface Network {
    void sendToServer(String id, Packet packet);

    void sendToClient(String id, IPlayer player, Packet packet);

    <P extends Packet> void registerServerHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ServerPacketHandler<P> handler);

    <P extends Packet> void registerClientHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ClientPacketHandler<P> handler);

    @FunctionalInterface
    interface PacketReader<P> {
        P read(IPacketBuffer buffer);
    }

    @FunctionalInterface
    interface ClientPacketHandler<P> {
        void accept(P packet);
    }

    @FunctionalInterface
    interface ServerPacketHandler<P> {
        void accept(P packet, IPlayer sender);
    }
}
