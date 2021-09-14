package com.github.franckyi.gameadapter.api.common.world;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;

public interface IEntity {
    ICompoundTag getData();

    int getEntityId();
}
