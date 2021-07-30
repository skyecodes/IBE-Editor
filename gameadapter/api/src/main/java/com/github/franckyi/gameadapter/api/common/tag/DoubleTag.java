package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

public interface DoubleTag extends Tag {
    static DoubleTag create() {
        return Game.getCommon().getTagFactory().createDoubleTag();
    }

    static DoubleTag create(double value) {
        return Game.getCommon().getTagFactory().createDoubleTag(value);
    }

    @Override
    default byte getType() {
        return Tag.DOUBLE_ID;
    }

    double getValue();

    @Override
    String toString();
}
