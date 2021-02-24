package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.WorldEntity;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.nbt.CompoundTag;

public class FabricWorldEntity implements WorldEntity {
    private final net.minecraft.entity.Entity entity;

    public FabricWorldEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public ObjectTag getTag() {
        CompoundTag compound = new CompoundTag();
        if (!entity.saveSelfToTag(compound)) {
            entity.toTag(compound);
        }
        return FabricTagFactory.INSTANCE.parseCompound(compound);
    }

    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }
}
