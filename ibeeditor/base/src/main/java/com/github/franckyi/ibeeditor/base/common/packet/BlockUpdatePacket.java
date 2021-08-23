package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.gameadapter.api.common.world.WorldBlockData;
import com.github.franckyi.ibeeditor.base.common.Packet;

public class BlockUpdatePacket implements Packet {
    private final WorldBlockData block;

    public BlockUpdatePacket(WorldBlockData block) {
        this.block = block;
    }

    public BlockUpdatePacket(IPacketBuffer buffer) {
        this(new WorldBlockData(buffer.readBlockState(), buffer.readTag(), buffer.readPos()));
    }

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.writeBlockState(block.getState());
        buffer.writeTag(block.getTag());
        buffer.writePos(block.getPos());
    }

    public WorldBlockData getBlock() {
        return block;
    }
}
