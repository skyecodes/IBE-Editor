package com.github.franckyi.guapi.node;

public class IntegerField extends NumberField<Integer> {

    public IntegerField() {
        this(0);
    }

    public IntegerField(int value) {
        this(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntegerField(int value, int min, int max) {
        super(value, min, max);
    }

    @Override
    protected Integer fromString(String s) throws NumberFormatException {
        return Integer.parseInt(s);
    }

    @Override
    protected String toString(Integer value) {
        return Integer.toString(value);
    }
}
