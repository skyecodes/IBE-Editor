package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.nbt.CompoundNBT;

public class ForgeEntity implements Entity {
    private final net.minecraft.entity.Entity entity;
    private ObjectTag tag;

    public ForgeEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
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

    @Override
    public int getId() {
        return tag.getType();
    }
}
