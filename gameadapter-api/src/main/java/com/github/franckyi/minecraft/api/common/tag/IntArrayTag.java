package com.github.franckyi.minecraft.api.common.tag;

public interface IntArrayTag extends Tag {
    @Override
    default byte getType() {
        return Tag.INT_ARRAY_ID;
    }

    int[] getValue();
}
