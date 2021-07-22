package com.github.franckyi.gameadapter.api.common.world;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;

public interface Block {
    CompoundTag getState();

    CompoundTag getData();
}
