package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.tag.LongArrayTag;

import java.util.List;

public class FabricLongArrayTag implements LongArrayTag {
    private final net.minecraft.nbt.LongArrayTag tag;

    public FabricLongArrayTag() {
        this(new long[0]);
    }

    public FabricLongArrayTag(long[] value) {
        this(new net.minecraft.nbt.LongArrayTag(value));
    }

    public FabricLongArrayTag(List<Long> value) {
        this(new net.minecraft.nbt.LongArrayTag(value));
    }

    public FabricLongArrayTag(net.minecraft.nbt.LongArrayTag tag) {
        this.tag = tag;
    }

    @Override
    public long[] getValue() {
        return tag.getLongArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.LongArrayTag get() {
        return tag;
    }
}
