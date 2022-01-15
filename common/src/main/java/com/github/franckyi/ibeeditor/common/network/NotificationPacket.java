package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.network.FriendlyByteBuf;

public class NotificationPacket {
    public static class Serializer implements PacketSerializer<NotificationPacket> {
        public static final Serializer INSTANCE = new Serializer();

        private Serializer() {
        }

        @Override
        public void write(NotificationPacket obj, FriendlyByteBuf buf) {
        }

        @Override
        public NotificationPacket read(FriendlyByteBuf buf) {
            return new NotificationPacket();
        }
    }
}
