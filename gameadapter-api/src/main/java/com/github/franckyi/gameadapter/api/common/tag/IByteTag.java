package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.api.Game;

public interface IByteTag extends ITag {
    static IByteTag create() {
        return Game.getCommon().getTagFactory().createByteTag();
    }

    static IByteTag create(byte value) {
        return Game.getCommon().getTagFactory().createByteTag(value);
    }

    byte getValue();

    @Override
    default String getStringValue() {
        return Byte.toString(getValue());
    }
}
