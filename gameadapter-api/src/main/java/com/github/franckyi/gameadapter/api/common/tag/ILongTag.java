package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.api.Game;

public interface ILongTag extends ITag {
    static ILongTag create() {
        return Game.getCommon().getTagFactory().createLongTag();
    }

    static ILongTag create(long value) {
        return Game.getCommon().getTagFactory().createLongTag(value);
    }

    long getValue();

    @Override
    default String getStringValue() {
        return Long.toString(getValue());
    }
}
