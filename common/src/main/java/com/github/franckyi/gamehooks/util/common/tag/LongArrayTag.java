package com.github.franckyi.gamehooks.util.common.tag;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongArrayTag extends AbstractTag<List<Long>> {
    public LongArrayTag() {
        this(new ArrayList<>());
    }

    public LongArrayTag(long[] value) {
        this(Arrays.asList(ArrayUtils.toObject(value)));
    }

    public LongArrayTag(List<Long> value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 12;
    }
}
