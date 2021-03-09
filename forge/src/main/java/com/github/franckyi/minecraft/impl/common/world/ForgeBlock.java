package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.Block;

public class ForgeBlock implements Block {
    private final CompoundTag state;
    private final CompoundTag data;

    public ForgeBlock(CompoundTag state, CompoundTag data) {
        this.state = state;
        this.data = data;
    }

    @Override
    public CompoundTag getState() {
        return state;
    }

    @Override
    public CompoundTag getData() {
        return data;
    }
}
