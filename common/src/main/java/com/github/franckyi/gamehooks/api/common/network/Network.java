package com.github.franckyi.gamehooks.api.common.network;

import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.util.common.ClientPacketHandler;
import com.github.franckyi.gamehooks.util.common.ServerPacketHandler;

import java.util.function.Function;

public interface Network {
    void sendToServer(String id, Packet packet);

    void sendToClient(String id, Player player, Packet packet);

    <P extends Packet> void registerServerHandler(String id, int id1, Class<P> msgClass, Function<Buffer, P> reader, ServerPacketHandler<P> handler);

    <P extends Packet> void registerClientHandler(String id, int id1, Class<P> msgClass, Function<Buffer, P> reader, ClientPacketHandler<P> handler);
}