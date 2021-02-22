package com.github.franckyi.gamehooks.api.common.network;

import com.github.franckyi.gamehooks.util.common.network.ClientPacketHandler;
import com.github.franckyi.gamehooks.util.common.network.ServerPacketHandler;

import java.util.function.Function;

public interface Network<E> {
    void sendToServer(String id, Packet packet);

    void sendToClient(String id, E entity, Packet packet);

    <P extends Packet> void registerServerHandler(String id, int id1, Class<P> msgClass, Function<Buffer, P> reader, ServerPacketHandler<P> handler);

    <P extends Packet> void registerClientHandler(String id, int id1, Class<P> msgClass, Function<Buffer, P> reader, ClientPacketHandler<P> handler);
}
