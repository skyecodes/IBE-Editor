package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.api.Game;

import java.util.List;

public interface IByteArrayTag extends ITag {
    static IByteArrayTag create() {
        return Game.getCommon().getTagFactory().createByteArrayTag();
    }

    static IByteArrayTag create(List<Byte> value) {
        return Game.getCommon().getTagFactory().createByteArrayTag(value);
    }

    byte[] getValue();
}
