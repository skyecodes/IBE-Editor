package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.api.Game;

public interface IDoubleTag extends ITag {
    static IDoubleTag create() {
        return Game.getCommon().getTagFactory().createDoubleTag();
    }

    static IDoubleTag create(double value) {
        return Game.getCommon().getTagFactory().createDoubleTag(value);
    }

    double getValue();

    @Override
    default String getStringValue() {
        return Double.toString(getValue());
    }
}
