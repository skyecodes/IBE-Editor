package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

public interface ShortTag extends Tag {
    static ShortTag create() {
        return Game.getCommon().getTagFactory().createShortTag();
    }

    static ShortTag create(short value) {
        return Game.getCommon().getTagFactory().createShortTag(value);
    }

    @Override
    default byte getType() {
        return Tag.SHORT_ID;
    }

    short getValue();

    @Override
    String toString();
}
