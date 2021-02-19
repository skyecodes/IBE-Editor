package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public interface TagFactory<T> {
    T parseObject(ObjectTag tag);

    ObjectTag parseCompound(T tag);
}
