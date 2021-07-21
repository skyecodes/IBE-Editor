package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.BlockPos;
import com.github.franckyi.gameadapter.api.common.network.Buffer;
import com.github.franckyi.gameadapter.api.common.network.Packet;
import com.github.franckyi.gameadapter.api.common.world.Block;

public class BlockUpdatePacket implements Packet {
    private final BlockPos blockPos;
    private final Block block;

    public BlockUpdatePacket(BlockPos blockPos, Block block) {
        this.blockPos = blockPos;
        this.block = block;
    }

    public BlockUpdatePacket(Buffer buffer) {
        this(buffer.readPos(), Game.getCommon().createBlock(buffer.readTag(), buffer.readTag()));
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
