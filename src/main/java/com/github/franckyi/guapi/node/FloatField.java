package com.github.franckyi.guapi.node;

import com.google.common.base.Strings;

public class FloatField extends NumberField<Float> {

    public FloatField() {
        this(0.0f);
    }

    public FloatField(float value) {
        this(value, Float.MIN_VALUE, Float.MAX_VALUE);
    }

    public FloatField(float value, float min, float max) {
        super(value, min, max);
    }

    @Override
    protected Float fromString(String s) throws NumberFormatException {
        return Strings.isNullOrEmpty(s) ? 0.0f : Float.parseFloat(s);
    }

    @Override
    protected String toString(Float value) {
        return Float.toString(value);
    }
}
