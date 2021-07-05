package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.WorldEntity;
import com.github.franckyi.minecraft.impl.common.nbt.FabricTagFactory;
import net.minecraft.nbt.NbtCompound;

public class FabricWorldEntity implements WorldEntity {
    private final net.minecraft.entity.Entity entity;

    public FabricWorldEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    @Override
    public CompoundTag getTag() {
        NbtCompound compound = new NbtCompound();
        if (!entity.saveSelfNbt(compound)) {
            entity.writeNbt(compound);
        }
        return FabricTagFactory.parseCompound(compound);
    }
}
