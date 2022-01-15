package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.server.level.ServerPlayer;

public class ServerNetworkHandler<P> extends NetworkHandler<P> {
    private final ServerPacketHandler<P> packetHandler;

    public ServerNetworkHandler(Class<P> type, String location, PacketSerializer<P> serializer, ServerPacketHandler<P> packetHandler) {
        super(type, location, serializer);
        this.packetHandler = packetHandler;
    }

    public ServerPacketHandler<P> getPacketHandler() {
        return packetHandler;
    }

    @FunctionalInterface
    public interface ServerPacketHandler<P> {
        void handle(P packet, ServerPlayer sender);
    }
}
