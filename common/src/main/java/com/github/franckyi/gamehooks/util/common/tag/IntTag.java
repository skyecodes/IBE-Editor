package com.github.franckyi.gamehooks.util.common.tag;

public class IntTag extends AbstractTag<Integer> {
    public IntTag() {
        this(0);
    }

    public IntTag(int value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 3;
    }

    @Override
    public String toString() {
        return Integer.toString(getValue());
    }
}
