package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gameadapter.api.common.network.Buffer;
import com.github.franckyi.gameadapter.api.common.network.Packet;
import com.github.franckyi.ibeeditor.impl.common.EditorType;

public class EditorCommandPacket implements Packet {
    public static final byte TARGET_WORLD = 0;
    public static final byte TARGET_ITEM = 1;
    public static final byte TARGET_BLOCK = 2;
    public static final byte TARGET_ENTITY = 3;
    public static final byte TARGET_SELF = 4;
    private final byte target;
    private final EditorType type;

    public EditorCommandPacket(byte target, EditorType type) {
        this.target = target;
        this.type = type;
    }

    public EditorCommandPacket(Buffer buffer) {
        this(buffer.readByte(), EditorType.from(buffer.readByte()));
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writeByte(target);
        buffer.writeByte(type.getId());
    }

    public byte getTarget() {
        return target;
    }

    public EditorType getType() {
        return type;
    }
}
