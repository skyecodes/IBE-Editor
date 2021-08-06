package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

public interface IFloatTag extends ITag {
    static IFloatTag create() {
        return Game.getCommon().getTagFactory().createFloatTag();
    }

    static IFloatTag create(float value) {
        return Game.getCommon().getTagFactory().createFloatTag(value);
    }

    float getValue();

    @Override
    default String getStringValue() {
        return Float.toString(getValue());
    }
}
