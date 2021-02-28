package com.github.franckyi.gamehooks.api.common.tag;

public interface ByteTag extends Tag {
    @Override
    default byte getType() {
        return Tag.BYTE_ID;
    }

    byte getValue();

    @Override
    String toString();
}
