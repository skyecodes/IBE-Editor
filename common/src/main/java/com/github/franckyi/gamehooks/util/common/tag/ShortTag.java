package com.github.franckyi.gamehooks.util.common.tag;

public class ShortTag extends AbstractTag<Short> {
    public ShortTag() {
        this((short) 0);
    }

    public ShortTag(short value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 2;
    }
}
