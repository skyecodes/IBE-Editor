package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.world.*;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

public class ForgeWorld implements World {
    private final net.minecraft.world.World world;

    public ForgeWorld(net.minecraft.world.World world) {
        this.world = world;
    }

    @Override
    public void setBlockInventoryItem(BlockPos blockPos, int slotId, Item item) {
        TileEntity tileEntity = world.getBlockEntity(blockPos.get());
        if (tileEntity instanceof IInventory) {
            ((IInventory) tileEntity).setItem(slotId, item.get());
        }
    }

    @Override
    public WorldBlock getBlock(BlockPos blockPos) {
        return new ForgeWorldBlock(blockPos, world.getBlockState(blockPos.get()), world.getBlockEntity(blockPos.get()));
    }

    @Override
    public void setBlockData(BlockPos blockPos, Block block) {
        TileEntity tileEntity = world.getBlockEntity(blockPos.get());
        if (tileEntity != null) {
            tileEntity.load(world.getBlockState(blockPos.get()), block.getData().get());
        }
    }

    @Override
    public WorldEntity getEntity(int entityId) {
        return new ForgeWorldEntity(world.getEntity(entityId));
    }

    @Override
    public void setEntityData(int entityId, Entity entity) {
        net.minecraft.entity.Entity worldEntity = world.getEntity(entityId);
        if (worldEntity != null) {
            worldEntity.load(entity.getTag().get());
        }
    }
}
