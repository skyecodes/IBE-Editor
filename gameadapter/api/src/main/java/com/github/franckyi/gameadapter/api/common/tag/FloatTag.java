package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

public interface FloatTag extends Tag {
    static FloatTag create() {
        return Game.getCommon().getTagFactory().createFloatTag();
    }

    static FloatTag create(float value) {
        return Game.getCommon().getTagFactory().createFloatTag(value);
    }

    @Override
    default byte getType() {
        return Tag.FLOAT_ID;
    }

    float getValue();

    @Override
    String toString();
}
