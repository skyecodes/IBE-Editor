package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;

public interface Item {
    CompoundTag getTag();

    <S> S get();
}
