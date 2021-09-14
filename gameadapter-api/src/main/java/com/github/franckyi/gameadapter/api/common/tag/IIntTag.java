package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.api.Game;

public interface IIntTag extends ITag {
    static IIntTag create() {
        return Game.getCommon().getTagFactory().createIntTag();
    }

    static IIntTag create(int value) {
        return Game.getCommon().getTagFactory().createIntTag(value);
    }

    int getValue();

    @Override
    default String getStringValue() {
        return Integer.toString(getValue());
    }
}
