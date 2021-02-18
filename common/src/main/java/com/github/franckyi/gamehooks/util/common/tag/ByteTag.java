package com.github.franckyi.gamehooks.util.common.tag;

public class ByteTag extends AbstractTag<Byte> {
    public ByteTag() {
        this((byte) 0);
    }

    public ByteTag(byte value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 1;
    }
}
