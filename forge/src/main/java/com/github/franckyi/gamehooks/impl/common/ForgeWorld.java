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
    public void setBlockInventoryItem(Pos pos, int slotId, Item item) {
        TileEntity tileEntity = world.getTileEntity(pos.getPos());
        if (tileEntity instanceof IInventory) {
            ((IInventory) tileEntity).setInventorySlotContents(slotId, item.getStack());
        }
    }

    @Override
    public WorldBlock getBlock(Pos pos) {
        return new ForgeWorldBlock(pos, world.getTileEntity(pos.getPos()));
    }

    @Override
    public void setBlockData(Pos pos, Block block) {
        TileEntity tileEntity = world.getTileEntity(pos.getPos());
        if (tileEntity != null) {
            tileEntity.read(world.getBlockState(pos.getPos()), ForgeTagFactory.parseObject(block.getTag()));
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
