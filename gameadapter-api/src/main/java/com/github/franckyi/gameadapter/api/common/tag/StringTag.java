package com.github.franckyi.gameadapter.api.common.tag;

public interface StringTag extends Tag {
    @Override
    default byte getType() {
        return Tag.STRING_ID;
    }

    String getValue();

    @Override
    String toString();
}
