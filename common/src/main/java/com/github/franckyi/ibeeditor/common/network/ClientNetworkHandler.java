package com.github.franckyi.ibeeditor.common.network;

public class ClientNetworkHandler<P> extends NetworkHandler<P> {
    private final ClientPacketHandler<P> packetHandler;

    public ClientNetworkHandler(Class<P> type, String location, PacketSerializer<P> serializer, ClientPacketHandler<P> packetHandler) {
        super(type, location, serializer);
        this.packetHandler = packetHandler;
    }

    public ClientPacketHandler<P> getPacketHandler() {
        return packetHandler;
    }

    @FunctionalInterface
    public interface ClientPacketHandler<P> {
        void handle(P packet);
    }
}
