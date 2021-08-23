package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.world.IBlockPos;

public class BlockInventoryItemUpdatePacket extends PlayerInventoryItemUpdatePacket {
    private final IBlockPos blockPos;

    public BlockInventoryItemUpdatePacket(IItemStack itemStack, int slotId, IBlockPos blockPos) {
        super(itemStack, slotId);
        this.blockPos = blockPos;
    }

    public BlockInventoryItemUpdatePacket(IPacketBuffer buffer) {
        super(buffer);
        blockPos = buffer.readPos();
    }

    @Override
    public void write(IPacketBuffer buffer) {
        super.write(buffer);
        buffer.writePos(blockPos);
    }

    public IBlockPos getPos() {
        return blockPos;
    }
}
