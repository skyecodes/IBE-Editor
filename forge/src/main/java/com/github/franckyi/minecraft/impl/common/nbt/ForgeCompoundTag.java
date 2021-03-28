package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.tag.ListTag;
import com.github.franckyi.minecraft.api.common.tag.Tag;
import net.minecraft.nbt.CompoundNBT;

import java.util.HashMap;
import java.util.Map;

public class ForgeCompoundTag implements CompoundTag {
    private final CompoundNBT tag;

    public ForgeCompoundTag() {
        this(new CompoundNBT());
    }

    public ForgeCompoundTag(CompoundNBT tag) {
        this.tag = tag;
    }

    public ForgeCompoundTag(Map<String, Tag> value) {
        this();
        value.forEach((s, tag1) -> tag.put(s, tag1.get()));
    }

    @Override
    public Map<String, Tag> getValue() {
        Map<String, Tag> value = new HashMap<>();
        for (String key : tag.keySet()) {
            value.put(key, ForgeTagFactory.from(tag.get(key)));
        }
        return value;
    }

    @Override
    public byte getByte(String key) {
        return tag.getByte(key);
    }

    @Override
    public short getShort(String key) {
        return tag.getShort(key);
    }

    @Override
    public int getInt(String key) {
        return tag.getInt(key);
    }

    @Override
    public long getLong(String key) {
        return tag.getLong(key);
    }

    @Override
    public float getFloat(String key) {
        return tag.getFloat(key);
    }

    @Override
    public double getDouble(String key) {
        return tag.getDouble(key);
    }

    @Override
    public byte[] getByteArray(String key) {
        return tag.getByteArray(key);
    }

    @Override
    public String getString(String key) {
        return tag.getString(key);
    }

    @Override
    public ListTag getList(String key, byte type) {
        return new ForgeListTag(tag.getList(key, type));
    }

    @Override
    public CompoundTag getCompound(String key) {
        return new ForgeCompoundTag(tag.getCompound(key));
    }

    @Override
    public int[] getIntArray(String key) {
        return tag.getIntArray(key);
    }

    @Override
    public long[] getLongArray(String key) {
        return tag.getLongArray(key);
    }

    @Override
    public void putString(String key, String value) {
        tag.putString(key, value);
    }

    @Override
    public void putInt(String key, int value) {
        tag.putInt(key, value);
    }

    @Override
    public CompoundTag copy() {
        return new ForgeCompoundTag(tag.copy());
    }

    @Override
    @SuppressWarnings("unchecked")
    public CompoundNBT get() {
        return tag;
    }
}
