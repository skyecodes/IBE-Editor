package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.nbt.CompoundNBT;

public class ForgeEntity implements Entity {
    private net.minecraft.entity.Entity entity;
    private ObjectTag tag;

    public ForgeEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    public ForgeEntity(ObjectTag tag) {
        this.tag = tag;
    }

    @Override
    public ObjectTag getTag() {
        if (tag == null) {
            CompoundNBT compound = new CompoundNBT();
            entity.writeUnlessPassenger(compound);
            tag = ForgeTagFactory.INSTANCE.parseCompound(compound);
        }
        return tag;
    }
}
