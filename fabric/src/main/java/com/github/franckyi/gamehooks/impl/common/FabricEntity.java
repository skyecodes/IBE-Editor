package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.nbt.CompoundTag;

public class FabricEntity implements Entity {
    private final net.minecraft.entity.Entity entity;
    private ObjectTag tag;

    public FabricEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public ObjectTag getTag() {
        if (tag == null) {
            CompoundTag compound = new CompoundTag();
            entity.saveSelfToTag(compound);
            tag = FabricTagFactory.INSTANCE.parseCompound(compound);
        }
        return tag;
    }

    @Override
    public int getId() {
        return entity.getEntityId();
    }
}
