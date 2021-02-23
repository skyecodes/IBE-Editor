package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.nbt.CompoundTag;

public class FabricEntity implements Entity {
    private net.minecraft.entity.Entity entity;
    private ObjectTag tag;

    public FabricEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    public FabricEntity(ObjectTag tag) {
        this.tag = tag;
    }

    @Override
    public ObjectTag getTag() {
        if (tag == null && entity != null) {
            CompoundTag compound = new CompoundTag();
            entity.saveSelfToTag(compound);
            tag = FabricTagFactory.INSTANCE.parseCompound(compound);
        }
        return tag;
    }
}
