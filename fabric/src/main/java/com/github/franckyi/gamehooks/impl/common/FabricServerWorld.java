package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.ServerWorld;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
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

    @Override
    public void setEntityData(int entityId, ObjectTag tag) {
        Entity entity = world.getEntityById(entityId);
        if (entity != null) {
            entity.fromTag(FabricTagFactory.INSTANCE.parseObject(tag));
        }
    }

    @Override
    public Block getBlock(Pos pos) {
        return new FabricBlock(world.getBlockEntity(pos.getPos()));
    }

    @Override
    public void setBlockData(Pos pos, ObjectTag tag) {
        BlockEntity blockEntity = world.getBlockEntity(pos.getPos());
        if (blockEntity != null) {
            blockEntity.fromTag(world.getBlockState(pos.getPos()), FabricTagFactory.INSTANCE.parseObject(tag));
        }
    }
}
