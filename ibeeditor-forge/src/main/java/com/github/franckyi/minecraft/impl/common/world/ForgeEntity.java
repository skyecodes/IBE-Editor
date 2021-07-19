package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.Entity;

public class ForgeEntity implements Entity {
    private final CompoundTag data;

    public ForgeEntity(CompoundTag data) {
        this.data = data;
    }

    @Override
    public CompoundTag getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.getString("id");
    }
}
