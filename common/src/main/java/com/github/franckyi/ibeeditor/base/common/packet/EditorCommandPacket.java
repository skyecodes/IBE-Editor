package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.Packet;
import net.minecraft.network.FriendlyByteBuf;

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

    public EditorCommandPacket(FriendlyByteBuf buffer) {
        this(buffer.readByte(), EditorType.from(buffer.readByte()));
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
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
