package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.ServerWorld;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;

public class FabricServerWorld implements ServerWorld {
    private final net.minecraft.server.world.ServerWorld world;

    public FabricServerWorld(net.minecraft.server.world.ServerWorld world) {
        this.world = world;
    }

    @Override
    public void setBlockInventoryItem(Pos pos, int slotId, Item item) {
        BlockEntity blockEntity = world.getBlockEntity(pos.getPos());
        if (blockEntity instanceof Inventory) {
            ((Inventory) blockEntity).setStack(slotId, item.getStack());
        }
    }
}
