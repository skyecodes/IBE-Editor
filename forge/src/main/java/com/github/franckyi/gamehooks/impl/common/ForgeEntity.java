package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;

public class ForgeEntity implements Entity {
    private final CompoundTag tag;

    public ForgeEntity(CompoundTag tag) {
        this.tag = tag;
    }

    @Override
    public CompoundTag getTag() {
        return tag;
    }
}
