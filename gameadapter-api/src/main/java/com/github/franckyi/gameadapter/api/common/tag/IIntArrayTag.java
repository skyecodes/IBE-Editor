package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.api.Game;

import java.util.List;

public interface IIntArrayTag extends ITag {
    static IIntArrayTag create() {
        return Game.getCommon().getTagFactory().createIntArrayTag();
    }

    static IIntArrayTag create(List<Integer> value) {
        return Game.getCommon().getTagFactory().createIntArrayTag(value);
    }

    int[] getValue();
}
