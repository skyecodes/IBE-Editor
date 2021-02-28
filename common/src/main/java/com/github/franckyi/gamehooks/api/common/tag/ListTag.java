package com.github.franckyi.gamehooks.api.common.tag;

import java.util.List;

public interface ListTag extends Tag {
    @Override
    default byte getType() {
        return Tag.LIST_ID;
    }

    List<Tag> getValue();

    short getShort(int index);

    int getInt(int index);

    float getFloat(int index);

    double getDouble(int index);

    String getString(int index);

    ListTag getList(int index);

    CompoundTag getCompound(int index);

    int[] getIntArray(int index);
}
