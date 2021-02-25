package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.WorldEntity;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.nbt.CompoundNBT;

public class ForgeWorldEntity implements WorldEntity {
    private final net.minecraft.entity.Entity entity;

    public ForgeWorldEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    @Override
    public ObjectTag getTag() {
        CompoundNBT compound = new CompoundNBT();
        if (!entity.writeUnlessRemoved(compound)) {
            entity.writeWithoutTypeId(compound);
        }
        return ForgeTagFactory.parseCompound(compound);
    }
}
