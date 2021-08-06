package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;

public interface IEntity {
    ICompoundTag getData();

    int getEntityId();
}
