package com.github.franckyi.gamehooks.api.common.tag;

public interface DoubleTag extends Tag {
    @Override
    default byte getType() {
        return Tag.DOUBLE_ID;
    }

    double getValue();

    @Override
    String toString();
}
