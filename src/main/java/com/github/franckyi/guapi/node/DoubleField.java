package com.github.franckyi.guapi.node;

public class DoubleField extends NumberField<Double> {

    public DoubleField() {
        this(0.0d);
    }

    public DoubleField(double value) {
        this(value, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public DoubleField(double value, double min, double max) {
        super(value, min, max);
    }

    @Override
    protected Double fromString(String s) throws NumberFormatException {
        return Double.parseDouble(s);
    }

    @Override
    protected String toString(Double value) {
        return Double.toString(value);
    }
}
