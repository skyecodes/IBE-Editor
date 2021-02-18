package com.github.franckyi.gamehooks.util.common.tag;

import java.util.ArrayList;
import java.util.List;

public class ArrayTag extends AbstractTag<List<Tag<?>>> {
    public ArrayTag() {
        this(new ArrayList<>());
    }

    public ArrayTag(List<Tag<?>> value) {
        super(value);
    }

    @Override
    public byte getType() {
        return 9;
    }
}
