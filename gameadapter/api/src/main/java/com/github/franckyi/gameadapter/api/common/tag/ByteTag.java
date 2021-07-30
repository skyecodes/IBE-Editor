package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

public interface ByteTag extends Tag {
    static ByteTag create() {
        return Game.getCommon().getTagFactory().createByteTag();
    }

    static ByteTag create(byte value) {
        return Game.getCommon().getTagFactory().createByteTag(value);
    }

    @Override
    default byte getType() {
        return Tag.BYTE_ID;
    }

    byte getValue();

    @Override
    String toString();
}
