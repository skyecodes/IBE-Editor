package com.github.franckyi.gamehooks.util.common.tag;

public class DoubleTag extends AbstractTag<Double> {
    public DoubleTag() {
        this(0);
    }

    public DoubleTag(double value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 6;
    }

    @Override
    public String toString() {
        return Double.toString(getValue());
    }
}
