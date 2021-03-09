package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.WorldEntity;
import com.github.franckyi.minecraft.impl.common.nbt.ForgeTagFactory;
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
    public CompoundTag getTag() {
        CompoundNBT compound = new CompoundNBT();
        if (!entity.writeUnlessRemoved(compound)) {
            entity.writeWithoutTypeId(compound);
        }
        return ForgeTagFactory.parseCompound(compound);
    }
}
