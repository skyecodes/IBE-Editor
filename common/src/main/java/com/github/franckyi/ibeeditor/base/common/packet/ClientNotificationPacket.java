package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.Packet;
import net.minecraft.network.FriendlyByteBuf;

public class ClientNotificationPacket implements Packet {
    private final boolean value;

    public ClientNotificationPacket() {
        value = true;
    }

    public ClientNotificationPacket(FriendlyByteBuf buffer) {
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
