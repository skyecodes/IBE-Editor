package com.github.franckyi.ibeeditor.util.item;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class TileEntityInventoryItemHandler extends CapabilityBasedItemHandler {

    private BlockPos blockPos;

    protected TileEntityInventoryItemHandler() {
    }

    public TileEntityInventoryItemHandler(ItemStack itemStack, int slotId, BlockPos blockPos) {
        super(itemStack, slotId);
        this.blockPos = blockPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeLong(blockPos.toLong());
    }

    @Override
    protected ICapabilitySerializable<?> getCapabilitySerializable(NetHandlerPlayServer server) {
        return server.player.world.getTileEntity(blockPos);
    }
}
