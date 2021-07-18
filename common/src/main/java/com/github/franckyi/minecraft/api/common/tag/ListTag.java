package com.github.franckyi.minecraft.api.common.tag;

import java.util.List;

public interface ListTag extends Tag {
    @Override
    default byte getType() {
        return Tag.LIST_ID;
    }

    List<Tag> getValue();

    int size();

    boolean isEmpty();

    String getString(int index);

    void addString(String value);
}
