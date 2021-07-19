package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.LongArrayTag;
import net.minecraft.nbt.LongArrayNBT;

import java.util.List;

public class ForgeLongArrayTag implements LongArrayTag {
    private final LongArrayNBT tag;

    public ForgeLongArrayTag() {
        this(new long[0]);
    }

    public ForgeLongArrayTag(long[] value) {
        this(new LongArrayNBT(value));
    }

    public ForgeLongArrayTag(List<Long> value) {
        this(new LongArrayNBT(value));
    }

    public ForgeLongArrayTag(LongArrayNBT tag) {
        this.tag = tag;
    }

    @Override
    public long[] getValue() {
        return tag.getAsLongArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public LongArrayNBT get() {
        return tag;
    }
}
