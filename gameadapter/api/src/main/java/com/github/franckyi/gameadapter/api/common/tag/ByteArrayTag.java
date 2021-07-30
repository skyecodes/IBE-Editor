package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

import java.util.List;

public interface ByteArrayTag extends Tag {
    static ByteArrayTag create() {
        return Game.getCommon().getTagFactory().createByteArrayTag();
    }

    static ByteArrayTag create(List<Byte> value) {
        return Game.getCommon().getTagFactory().createByteArrayTag(value);
    }

    @Override
    default byte getType() {
        return Tag.BYTE_ARRAY_ID;
    }

    byte[] getValue();
}
