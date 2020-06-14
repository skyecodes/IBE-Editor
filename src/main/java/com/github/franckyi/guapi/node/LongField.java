package com.github.franckyi.guapi.node;

public class LongField extends NumberField<Long> {

    public LongField() {
        this(0L);
    }

    public LongField(long value) {
        this(value, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public LongField(long value, long min, long max) {
        super(value, min, max);
    }

    @Override
    protected Long fromString(String s) throws NumberFormatException {
        return Long.parseLong(s);
    }

    @Override
    protected String toString(Long value) {
        return Long.toString(value);
    }
}
