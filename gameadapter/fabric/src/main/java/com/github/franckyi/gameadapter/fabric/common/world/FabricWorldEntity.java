package com.github.franckyi.gameadapter.fabric.common.world;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.world.WorldEntity;
import com.github.franckyi.gameadapter.fabric.common.nbt.FabricTagFactory;
import net.minecraft.nbt.NbtCompound;

public class FabricWorldEntity implements WorldEntity {
    private final net.minecraft.entity.Entity entity;

    public FabricWorldEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public int getEntityId() {
        return entity.getId();
    }

    @Override
    public CompoundTag getData() {
        NbtCompound compound = new NbtCompound();
        if (!entity.saveSelfNbt(compound)) {
            entity.writeNbt(compound);
        }
        return FabricTagFactory.parseCompound(compound);
    }
}
