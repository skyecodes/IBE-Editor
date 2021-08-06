package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

public interface IShortTag extends ITag {
    static IShortTag create() {
        return Game.getCommon().getTagFactory().createShortTag();
    }

    static IShortTag create(short value) {
        return Game.getCommon().getTagFactory().createShortTag(value);
    }

    short getValue();

    @Override
    default String getStringValue() {
        return Short.toString(getValue());
    }
}
