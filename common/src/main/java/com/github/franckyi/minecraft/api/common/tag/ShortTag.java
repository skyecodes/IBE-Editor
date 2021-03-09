package com.github.franckyi.minecraft.api.common.tag;

public interface ShortTag extends Tag {
    @Override
    default byte getType() {
        return Tag.SHORT_ID;
    }

    short getValue();

    @Override
    String toString();
}
