package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

public interface StringTag extends Tag {
    static StringTag create() {
        return Game.getCommon().getTagFactory().createStringTag();
    }

    static StringTag create(String value) {
        return Game.getCommon().getTagFactory().createStringTag(value);
    }

    @Override
    default byte getType() {
        return Tag.STRING_ID;
    }

    String getValue();

    @Override
    String toString();
}
