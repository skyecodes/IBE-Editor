package com.github.franckyi.gamehooks.util.common.tag;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ByteArrayTag extends AbstractTag<List<Byte>> {
    public ByteArrayTag() {
        this(new ArrayList<>());
    }

    public ByteArrayTag(byte[] value) {
        this(Arrays.asList(ArrayUtils.toObject(value)));
    }

    public ByteArrayTag(List<Byte> value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 7;
    }
}
