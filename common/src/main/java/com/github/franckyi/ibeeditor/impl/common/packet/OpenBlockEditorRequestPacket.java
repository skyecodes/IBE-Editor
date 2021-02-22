package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.network.Buffer;
import com.github.franckyi.gamehooks.api.common.network.Packet;

public class OpenBlockEditorRequestPacket implements Packet {
    private final Pos pos;
    private final boolean nbt;

    public OpenBlockEditorRequestPacket(Pos pos, boolean nbt) {
        this.pos = pos;
        this.nbt = nbt;
    }

    public OpenBlockEditorRequestPacket(Buffer buffer) {
        this(buffer.readPos(), buffer.readBoolean());
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writePos(pos);
        buffer.writeBoolean(nbt);
    }

    public Pos getPos() {
        return pos;
    }

    public boolean isNBT() {
        return nbt;
    }
}
