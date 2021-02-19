package com.github.franckyi.gamehooks.util.common.tag;

public class FloatTag extends AbstractTag<Float> {
    public FloatTag() {
        this(0F);
    }

    public FloatTag(float value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 5;
    }

    @Override
    public String toString() {
        return Float.toString(getValue());
    }
}
