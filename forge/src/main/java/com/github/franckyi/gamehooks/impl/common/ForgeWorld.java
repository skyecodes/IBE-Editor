package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.*;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

public class ForgeWorld implements World {
    private final net.minecraft.world.World world;

    public ForgeWorld(net.minecraft.world.World world) {
        this.world = world;
    }

    @Override
    public void setBlockInventoryItem(BlockPos blockPos, int slotId, Item item) {
        TileEntity tileEntity = world.getTileEntity(blockPos.get());
        if (tileEntity instanceof IInventory) {
            ((IInventory) tileEntity).setInventorySlotContents(slotId, item.get());
        }
    }

    @Override
    public WorldBlock getBlock(BlockPos blockPos) {
        return new ForgeWorldBlock(blockPos, world.getBlockState(blockPos.get()), world.getTileEntity(blockPos.get()));
    }

    @Override
    public void setBlockData(BlockPos blockPos, Block block) {
        TileEntity tileEntity = world.getTileEntity(blockPos.get());
        if (tileEntity != null) {
            tileEntity.read(world.getBlockState(blockPos.get()), ForgeTagFactory.parseObject(block.getData()));
        }
    }

    @Override
    public WorldEntity getEntity(int entityId) {
        return new ForgeWorldEntity(world.getEntityByID(entityId));
    }

    @Override
    public void setEntityData(int entityId, Entity entity) {
        net.minecraft.entity.Entity worldEntity = world.getEntityByID(entityId);
        if (worldEntity != null) {
            worldEntity.read(ForgeTagFactory.parseObject(entity.getTag()));
        }
    }
}
