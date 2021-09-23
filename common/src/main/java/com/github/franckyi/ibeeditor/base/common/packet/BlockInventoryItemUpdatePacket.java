package com.github.franckyi.ibeeditor.base.common.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public class BlockInventoryItemUpdatePacket extends PlayerInventoryItemUpdatePacket {
    private final BlockPos blockPos;

    public BlockInventoryItemUpdatePacket(ItemStack itemStack, int slotId, BlockPos blockPos) {
        super(itemStack, slotId);
        this.blockPos = blockPos;
    }

    public BlockInventoryItemUpdatePacket(FriendlyByteBuf buffer) {
        super(buffer);
        blockPos = buffer.readBlockPos();
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        super.write(buffer);
        buffer.writeBlockPos(blockPos);
    }

    public BlockPos getPos() {
        return blockPos;
    }
}
