package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

import java.util.List;

public interface LongArrayTag extends Tag {
    static LongArrayTag create() {
        return Game.getCommon().getTagFactory().createLongArrayTag();
    }

    static LongArrayTag create(List<Long> value) {
        return Game.getCommon().getTagFactory().createLongArrayTag(value);
    }

    @Override
    default byte getType() {
        return Tag.LONG_ARRAY_ID;
    }

    long[] getValue();
}
