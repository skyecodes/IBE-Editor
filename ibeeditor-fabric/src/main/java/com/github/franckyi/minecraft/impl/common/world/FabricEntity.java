package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.Entity;

public class FabricEntity implements Entity {
    private final CompoundTag data;

    public FabricEntity(CompoundTag data) {
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
