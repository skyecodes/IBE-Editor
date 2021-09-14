package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.gameadapter.api.common.world.IBlockPos;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.Packet;

public class BlockEditorRequestPacket implements Packet {
    private final IBlockPos pos;
    private final EditorType type;

    public BlockEditorRequestPacket(IBlockPos pos, EditorType type) {
        this.pos = pos;
        this.type = type;
    }

    public BlockEditorRequestPacket(IPacketBuffer buffer) {
        this(buffer.readPos(), EditorType.from(buffer.readByte()));
    }

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.writePos(pos);
        buffer.writeByte(type.getId());
    }

    public IBlockPos getPos() {
        return pos;
    }

    public EditorType getType() {
        return type;
    }
}
