package com.github.franckyi.gameadapter.api.common.tag;

import java.util.List;

public interface ListTag extends Tag, List<Tag> {
    @Override
    default byte getType() {
        return Tag.LIST_ID;
    }

    String getString(int index);

    CompoundTag getCompound(int index);

    void addString(String value);

    void addTag(Tag tag);
}
