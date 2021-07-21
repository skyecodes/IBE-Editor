package com.github.franckyi.gameadapter.api.common.tag;

public interface LongArrayTag extends Tag {
    @Override
    default byte getType() {
        return Tag.LONG_ARRAY_ID;
    }

    long[] getValue();
}
