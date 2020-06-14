package com.github.franckyi.guapi.node;

public class ShortField extends NumberField<Short> {

    public ShortField() {
        this((short) 0);
    }

    public ShortField(short value) {
        this(value, Short.MIN_VALUE, Short.MAX_VALUE);
    }

    public ShortField(short value, short min, short max) {
        super(value, min, max);
    }

    @Override
    protected Short fromString(String s) throws NumberFormatException {
        return Short.parseShort(s);
    }

    @Override
    protected String toString(Short value) {
        return Short.toString(value);
    }
}
