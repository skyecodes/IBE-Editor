package com.github.franckyi.gameadapter.api.common.tag;

import com.github.franckyi.gameadapter.Game;

public interface IStringTag extends ITag {
    static IStringTag create() {
        return Game.getCommon().getTagFactory().createStringTag();
    }

    static IStringTag create(String value) {
        return Game.getCommon().getTagFactory().createStringTag(value);
    }

    String getValue();

    @Override
    default String getStringValue() {
        return getValue();
    }
}
