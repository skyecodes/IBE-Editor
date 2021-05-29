package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.LongTag;
import net.minecraft.nbt.LongNBT;

public class ForgeLongTag implements LongTag {
    private final LongNBT tag;

    public ForgeLongTag() {
        this(0L);
    }

    public ForgeLongTag(long value) {
        this(LongNBT.valueOf(value));
    }

    public ForgeLongTag(LongNBT tag) {
        this.tag = tag;
    }

    @Override
    public long getValue() {
        return tag.getAsLong();
    }

    @Override
    @SuppressWarnings("unchecked")
    public LongNBT get() {
        return tag;
    }

    @Override
    public String toString() {
        return Long.toString(getValue());
    }
}
