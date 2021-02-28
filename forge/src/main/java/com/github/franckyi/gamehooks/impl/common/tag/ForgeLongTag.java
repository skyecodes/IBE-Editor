package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.tag.LongTag;
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
        return tag.getLong();
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
