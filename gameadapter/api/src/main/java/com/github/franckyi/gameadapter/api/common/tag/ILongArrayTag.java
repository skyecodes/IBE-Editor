package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

import java.util.List;

public interface ILongArrayTag extends ITag {
    static ILongArrayTag create() {
        return Game.getCommon().getTagFactory().createLongArrayTag();
    }

    static ILongArrayTag create(List<Long> value) {
        return Game.getCommon().getTagFactory().createLongArrayTag(value);
    }

    long[] getValue();
}
