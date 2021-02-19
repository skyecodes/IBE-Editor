package com.github.franckyi.gamehooks.util.common.tag;

public class LongTag extends AbstractTag<Long> {
    public LongTag() {
        this(0L);
    }

    public LongTag(long value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 4;
    }

    @Override
    public String toString() {
        return Long.toString(getValue());
    }
}
