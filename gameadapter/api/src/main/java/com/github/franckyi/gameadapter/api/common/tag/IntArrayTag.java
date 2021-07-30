package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

import java.util.List;

public interface IntArrayTag extends Tag {
    static IntArrayTag create() {
        return Game.getCommon().getTagFactory().createIntArrayTag();
    }

    static IntArrayTag create(List<Integer> value) {
        return Game.getCommon().getTagFactory().createIntArrayTag(value);
    }

    @Override
    default byte getType() {
        return Tag.INT_ARRAY_ID;
    }

    int[] getValue();
}
