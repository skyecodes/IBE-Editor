package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;

public interface Block {
    CompoundTag getState();

    CompoundTag getData();
}
