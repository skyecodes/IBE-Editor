package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.ServerWorld;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

public class ForgeServerWorld implements ServerWorld {
    private final net.minecraft.world.server.ServerWorld world;

    public ForgeServerWorld(net.minecraft.world.server.ServerWorld world) {
        this.world = world;
    }

    @Override
    public void setBlockInventoryItem(Pos pos, int slotId, Item item) {
        TileEntity tileEntity = world.getTileEntity(pos.getPos());
        if (tileEntity instanceof IInventory) {
            ((IInventory) tileEntity).setInventorySlotContents(slotId, item.getStack());
        }
    }
}
