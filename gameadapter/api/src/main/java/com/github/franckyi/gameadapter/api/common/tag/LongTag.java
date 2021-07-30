package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

public interface LongTag extends Tag {
    static LongTag create() {
        return Game.getCommon().getTagFactory().createLongTag();
    }

    static LongTag create(long value) {
        return Game.getCommon().getTagFactory().createLongTag(value);
    }

    @Override
    default byte getType() {
        return Tag.LONG_ID;
    }

    long getValue();

    @Override
    String toString();
}
