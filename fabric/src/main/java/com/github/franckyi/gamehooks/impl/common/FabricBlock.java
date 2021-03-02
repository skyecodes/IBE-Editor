package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;

public class FabricBlock implements Block {
    private final CompoundTag state;
    private final CompoundTag data;

    public FabricBlock(CompoundTag state, CompoundTag data) {
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
