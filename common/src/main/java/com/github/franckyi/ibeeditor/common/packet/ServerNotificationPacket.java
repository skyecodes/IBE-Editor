package com.github.franckyi.ibeeditor.common.packet;

import com.github.franckyi.ibeeditor.common.Packet;
import net.minecraft.network.FriendlyByteBuf;

public class ServerNotificationPacket implements Packet {
    private final boolean value;

    public ServerNotificationPacket() {
        value = true;
    }

    public ServerNotificationPacket(FriendlyByteBuf buffer) {
        value = buffer.readBoolean();
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeBoolean(value);
    }

    public boolean getValue() {
        return value;
    }
}
