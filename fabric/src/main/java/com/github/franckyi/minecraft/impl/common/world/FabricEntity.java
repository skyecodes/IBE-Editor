package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.Entity;

public class FabricEntity implements Entity {
    private final CompoundTag tag;

    public FabricEntity(CompoundTag tag) {
        this.tag = tag;
    }

    @Override
    public CompoundTag getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return tag.getString("id");
    }
}
