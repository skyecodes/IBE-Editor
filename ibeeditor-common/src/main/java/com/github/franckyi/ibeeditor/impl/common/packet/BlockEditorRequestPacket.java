package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.ibeeditor.impl.common.EditorType;
import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.network.Buffer;
import com.github.franckyi.minecraft.api.common.network.Packet;

public class BlockEditorRequestPacket implements Packet {
    private final BlockPos blockPos;
    private final EditorType type;

    public BlockEditorRequestPacket(BlockPos blockPos, EditorType type) {
        this.blockPos = blockPos;
        this.type = type;
    }

    public BlockEditorRequestPacket(Buffer buffer) {
        this(buffer.readPos(), EditorType.from(buffer.readByte()));
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writePos(blockPos);
        buffer.writeByte(type.getId());
    }

    public BlockPos getPos() {
        return blockPos;
    }

    public EditorType getType() {
        return type;
    }
}
