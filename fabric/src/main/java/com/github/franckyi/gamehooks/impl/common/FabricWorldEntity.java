package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.WorldEntity;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.github.franckyi.gamehooks.impl.common.tag.FabricTagFactory;

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
        net.minecraft.nbt.CompoundTag compound = new net.minecraft.nbt.CompoundTag();
        if (!entity.saveSelfToTag(compound)) {
            entity.toTag(compound);
        }
        return FabricTagFactory.parseCompound(compound);
    }
}
