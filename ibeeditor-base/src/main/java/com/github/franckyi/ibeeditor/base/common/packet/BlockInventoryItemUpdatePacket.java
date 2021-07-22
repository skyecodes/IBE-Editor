package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.api.common.BlockPos;
import com.github.franckyi.gameadapter.api.common.network.Buffer;
import com.github.franckyi.gameadapter.api.common.world.Item;

public class BlockInventoryItemUpdatePacket extends PlayerInventoryItemUpdatePacket {
    private final BlockPos blockPos;

    public BlockInventoryItemUpdatePacket(Item item, int slotId, BlockPos blockPos) {
        super(item, slotId);
        this.blockPos = blockPos;
    }

    public BlockInventoryItemUpdatePacket(Buffer buffer) {
        super(buffer);
        blockPos = buffer.readPos();
    }

    @Override
    public void write(Buffer buffer) {
        super.write(buffer);
        buffer.writePos(blockPos);
    }

    public BlockPos getPos() {
        return blockPos;
    }
}
