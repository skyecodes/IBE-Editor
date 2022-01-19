package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public abstract class NetworkHandler<P> {
    private static int count;
    private final Class<P> type;
    private final ResourceLocation location;
    private final int id;
    private final PacketSerializer<P> serializer;

    protected NetworkHandler(Class<P> type, String location, PacketSerializer<P> serializer) {
        this.type = type;
        this.location = new ResourceLocation(location);
        this.id = count++;
        this.serializer = serializer;
    }

    public Class<P> getType() {
        return type;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public PacketSerializer<P> getSerializer() {
        return serializer;
    }

    public static class Client<P> extends NetworkHandler<P> {
        private final ClientPacketHandler<P> packetHandler;

        public Client(Class<P> type, String location, PacketSerializer<P> serializer, ClientPacketHandler<P> packetHandler) {
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

    public static class Server<P> extends NetworkHandler<P> {
        private final ServerPacketHandler<P> packetHandler;

        public Server(Class<P> type, String location, PacketSerializer<P> serializer, ServerPacketHandler<P> packetHandler) {
            super(type, location, serializer);
            this.packetHandler = packetHandler;
        }

        public ServerPacketHandler<P> getPacketHandler() {
            return packetHandler;
        }

        @FunctionalInterface
        public interface ServerPacketHandler<P> {
            void handle(ServerPlayer sender, P packet);
        }
    }
}
