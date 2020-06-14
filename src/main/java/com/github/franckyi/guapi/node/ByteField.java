package com.github.franckyi.guapi.node;

public class ByteField extends NumberField<Byte> {

    public ByteField() {
        this((byte) 0);
    }

    public ByteField(byte value) {
        this(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    public ByteField(byte value, byte min, byte max) {
        super(value, min, max);
    }

    @Override
    protected Byte fromString(String s) throws NumberFormatException {
        return Byte.parseByte(s);
    }

    @Override
    protected String toString(Byte value) {
        return Byte.toString(value);
    }
}
