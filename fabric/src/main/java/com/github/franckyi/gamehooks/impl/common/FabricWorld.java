package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.*;
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
    public void setBlockData(Pos pos, Block block) {
        BlockEntity blockEntity = world.getBlockEntity(pos.getPos());
        if (blockEntity != null) {
            blockEntity.fromTag(world.getBlockState(pos.getPos()), FabricTagFactory.parseObject(block.getTag()));
        }
    }

    @Override
    public WorldEntity getEntity(int entityId) {
        return new FabricWorldEntity(world.getEntityById(entityId));
    }

    @Override
    public void setEntityData(int entityId, Entity entity) {
        net.minecraft.entity.Entity worldEntity = world.getEntityById(entityId);
        if (worldEntity != null) {
            worldEntity.fromTag(FabricTagFactory.parseObject(entity.getTag()));
        }
    }
}
