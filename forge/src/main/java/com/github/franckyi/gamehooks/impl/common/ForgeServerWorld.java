package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.ServerWorld;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.entity.Entity;
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
    public void setEntityData(int entityId, ObjectTag tag) {
        Entity entity = world.getEntityByID(entityId);
        if (entity != null) {
            entity.read(ForgeTagFactory.INSTANCE.parseObject(tag));
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
}
