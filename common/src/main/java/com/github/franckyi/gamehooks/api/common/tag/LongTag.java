package com.github.franckyi.gamehooks.api.common.tag;

public interface LongTag extends Tag {
    @Override
    default byte getType() {
        return Tag.LONG_ID;
    }

    long getValue();

    @Override
    String toString();
}
