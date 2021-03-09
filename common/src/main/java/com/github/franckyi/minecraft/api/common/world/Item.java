package com.github.franckyi.minecraft.api.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;

public interface Item {
    CompoundTag getTag();

    <S> S get();
}
