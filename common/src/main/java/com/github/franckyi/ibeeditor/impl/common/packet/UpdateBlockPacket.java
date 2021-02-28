package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.BlockPos;
import com.github.franckyi.gamehooks.api.common.network.Buffer;
import com.github.franckyi.gamehooks.api.common.network.Packet;

public class UpdateBlockPacket implements Packet {
    private final BlockPos blockPos;
    private final Block block;

    public UpdateBlockPacket(BlockPos blockPos, Block block) {
        this.blockPos = blockPos;
        this.block = block;
    }

    public UpdateBlockPacket(Buffer buffer) {
        this(buffer.readPos(), GameHooks.common().createBlock(buffer.readTag(), buffer.readTag()));
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writePos(blockPos);
        buffer.writeTag(block.getState());
        buffer.writeTag(block.getData());
    }

    public BlockPos getPos() {
        return blockPos;
    }

    public Block getBlock() {
        return block;
    }
}
