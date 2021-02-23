package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
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

    @Override
    public Block getBlock(Pos pos) {
        return new ForgeBlock(world.getTileEntity(pos.getPos()));
    }

    @Override
    public void setBlockData(Pos pos, ObjectTag tag) {
        TileEntity tileEntity = world.getTileEntity(pos.getPos());
        if (tileEntity != null) {
            tileEntity.read(world.getBlockState(pos.getPos()), ForgeTagFactory.INSTANCE.parseObject(tag));
        }
    }

    @Override
    public Entity getEntity(int entityId) {
        return new ForgeEntity(world.getEntityByID(entityId));
    }

    @Override
    public void setEntityData(int entityId, ObjectTag tag) {
        net.minecraft.entity.Entity entity = world.getEntityByID(entityId);
        if (entity != null) {
            entity.read(ForgeTagFactory.INSTANCE.parseObject(tag));
        }
    }
}
