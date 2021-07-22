package com.github.franckyi.gameadapter.forge.common.world;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.world.Block;

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

    @Override
    public String toString() {
        return data == null ? "" : data.getString("id");
    }
}
