package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.minecraft.api.common.network.Buffer;
import com.github.franckyi.minecraft.api.common.network.Packet;

public class EditorCommandPacket implements Packet {
    public static final byte WORLD = 0;
    public static final byte ITEM = 1;
    public static final byte BLOCK = 2;
    public static final byte ENTITY = 3;
    public static final byte SELF = 4;
    private final byte type;
    private final boolean nbt;

    public EditorCommandPacket(byte type, boolean nbt) {
        this.type = type;
        this.nbt = nbt;
    }

    public EditorCommandPacket(Buffer buffer) {
        this(buffer.readByte(), buffer.readBoolean());
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writeByte(type);
        buffer.writeBoolean(nbt);
    }

    public byte getType() {
        return type;
    }

    public boolean isNBT() {
        return nbt;
    }
}