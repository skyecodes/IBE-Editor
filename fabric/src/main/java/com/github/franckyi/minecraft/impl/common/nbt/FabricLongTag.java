package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.LongTag;

public class FabricLongTag implements LongTag {
    private final net.minecraft.nbt.LongTag tag;

    public FabricLongTag() {
        this(0L);
    }

    public FabricLongTag(long value) {
        this(net.minecraft.nbt.LongTag.of(value));
    }

    public FabricLongTag(net.minecraft.nbt.LongTag tag) {
        this.tag = tag;
    }

    @Override
    public long getValue() {
        return tag.getLong();
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.LongTag get() {
        return tag;
    }

    @Override
    public String toString() {
        return Long.toString(getValue());
    }
}
