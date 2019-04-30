package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.network.PacketBuffer;

public abstract class EmptyMessage implements IMessage {

    protected EmptyMessage() {
    }

    protected EmptyMessage(PacketBuffer buffer) {
    }

    @Override
    public void write(PacketBuffer buffer) {
    }
}
