package com.github.franckyi.gameadapter.forge.common.world;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.world.WorldEntity;
import com.github.franckyi.gameadapter.forge.common.nbt.ForgeTagFactory;
import net.minecraft.nbt.CompoundNBT;

public class ForgeWorldEntity implements WorldEntity {
    private final net.minecraft.entity.Entity entity;

    public ForgeWorldEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public int getEntityId() {
        return entity.getId();
    }

    @Override
    public CompoundTag getData() {
        CompoundNBT compound = new CompoundNBT();
        if (!entity.saveAsPassenger(compound)) {
            entity.saveWithoutId(compound);
        }
        return ForgeTagFactory.parseCompound(compound);
    }
}
