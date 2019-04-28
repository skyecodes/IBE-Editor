package com.github.franckyi.ibeeditor.network.item;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.items.CapabilityItemHandler;

public class BlockInventoryItemEditorMessage extends PlayerInventoryItemEditorMessage {

    protected final BlockPos blockPos;

    public BlockInventoryItemEditorMessage(ItemStack itemStack, int slotIndex, BlockPos blockPos) {
        super(itemStack, slotIndex);
        this.blockPos = blockPos;
    }

    public BlockInventoryItemEditorMessage(PacketBuffer buffer) {
        super(buffer);
        this.blockPos = buffer.readBlockPos();
    }

    @Override
    public void write(PacketBuffer buffer) {
        super.write(buffer);
        buffer.writeBlockPos(blockPos);
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        TileEntity te = context.getSender().getServerWorld().getTileEntity(blockPos);
        if (te != null) {
            te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
                itemHandler.extractItem(slotIndex, itemHandler.getStackInSlot(slotIndex).getCount(), false);
                itemHandler.insertItem(slotIndex, itemStack, false);
            });
        }
    }

}
