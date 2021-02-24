package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;

public class FabricWorld implements World {
    private final net.minecraft.world.World world;

    public FabricWorld(net.minecraft.world.World world) {
        this.world = world;
    }

    @Override
    public void setBlockInventoryItem(Pos pos, int slotId, Item item) {
        BlockEntity blockEntity = world.getBlockEntity(pos.getPos());
        if (blockEntity instanceof Inventory) {
            ((Inventory) blockEntity).setStack(slotId, item.getStack());
        }
    }

    @Override
    public WorldBlock getBlock(Pos pos) {
        return new FabricWorldBlock(pos, world.getBlockEntity(pos.getPos()));
    }

    @Override
    public void setBlockData(Pos pos, ObjectTag tag) {
        BlockEntity blockEntity = world.getBlockEntity(pos.getPos());
        if (blockEntity != null) {
            blockEntity.fromTag(world.getBlockState(pos.getPos()), FabricTagFactory.INSTANCE.parseObject(tag));
        }
    }

    @Override
    public WorldEntity getEntity(int entityId) {
        return new FabricWorldEntity(world.getEntityById(entityId));
    }

    @Override
    public void setEntityData(int entityId, ObjectTag tag) {
        net.minecraft.entity.Entity entity = world.getEntityById(entityId);
        if (entity != null) {
            entity.fromTag(FabricTagFactory.INSTANCE.parseObject(tag));
        }
    }
}
