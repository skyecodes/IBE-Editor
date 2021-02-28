package com.github.franckyi.gamehooks.api.common.tag;

public interface IntArrayTag extends Tag {
    @Override
    default byte getType() {
        return Tag.INT_ARRAY_ID;
    }

    int[] getValue();
}
