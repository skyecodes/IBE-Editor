package com.github.franckyi.ibeeditor.common.network;

public final class ModNotificationPacket {
    public record Client() {
        public static final Client INSTANCE = new Client();
        public static final PacketSerializer<Client> SERIALIZER = PacketSerializer.empty(INSTANCE);
    }

    public record Server() {
        public static final Server INSTANCE = new Server();
        public static final PacketSerializer<Server> SERIALIZER = PacketSerializer.empty(INSTANCE);
    }
}
