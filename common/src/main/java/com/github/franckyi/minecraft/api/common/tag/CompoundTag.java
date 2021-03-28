package com.github.franckyi.minecraft.api.common.tag;

import java.util.Map;

public interface CompoundTag extends Tag {
    @Override
    default byte getType() {
        return Tag.COMPOUND_ID;
    }

    Map<String, Tag> getValue();

    byte getByte(String key);

    short getShort(String key);

    int getInt(String key);

    long getLong(String key);

    float getFloat(String key);

    double getDouble(String key);

    byte[] getByteArray(String key);

    String getString(String key);

    ListTag getList(String key, byte type);

    CompoundTag getCompound(String key);

    int[] getIntArray(String key);

    long[] getLongArray(String key);

    void putString(String key, String value);

    void putInt(String key, int value);

    CompoundTag copy();
}
