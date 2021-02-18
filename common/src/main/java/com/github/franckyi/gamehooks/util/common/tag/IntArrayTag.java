package com.github.franckyi.gamehooks.util.common.tag;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntArrayTag extends AbstractTag<List<Integer>> {
    public IntArrayTag() {
        this(new ArrayList<>());
    }

    public IntArrayTag(int[] value) {
        this(Arrays.asList(ArrayUtils.toObject(value)));
    }

    public IntArrayTag(List<Integer> value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 11;
    }
}
