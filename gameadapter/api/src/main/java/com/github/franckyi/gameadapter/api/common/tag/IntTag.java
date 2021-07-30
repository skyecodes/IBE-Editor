package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

public interface IntTag extends Tag {
    static IntTag create() {
        return Game.getCommon().getTagFactory().createIntTag();
    }

    static IntTag create(int value) {
        return Game.getCommon().getTagFactory().createIntTag(value);
    }

    @Override
    default byte getType() {
        return Tag.INT_ID;
    }

    int getValue();

    @Override
    String toString();
}
