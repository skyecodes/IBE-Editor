package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public interface Item {
    ObjectTag getTag();

    <S> S getStack();
}
