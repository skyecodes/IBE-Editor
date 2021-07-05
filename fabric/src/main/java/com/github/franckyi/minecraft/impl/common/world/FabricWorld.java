package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.world.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;

public class FabricWorld implements World {
    private final net.minecraft.world.World world;

    public FabricWorld(net.minecraft.world.World world) {
        this.world = world;
    }

    @Override
    public void setBlockInventoryItem(BlockPos blockPos, int slotId, Item item) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos.get());
        if (blockEntity instanceof Inventory) {
            ((Inventory) blockEntity).setStack(slotId, item.get());
        }
    }

    @Override
    public WorldBlock getBlock(BlockPos blockPos) {
        return new FabricWorldBlock(blockPos, world.getBlockState(blockPos.get()), world.getBlockEntity(blockPos.get()));
    }

    @Override
    public void setBlockData(BlockPos blockPos, Block block) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos.get());
        if (blockEntity != null) {
            blockEntity.fromTag(world.getBlockState(blockPos.get()), block.getData().get());
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
            worldEntity.readNbt(entity.getTag().get());
        }
    }
}
