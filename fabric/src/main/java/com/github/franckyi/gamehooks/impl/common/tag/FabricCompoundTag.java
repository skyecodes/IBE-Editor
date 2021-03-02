package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.github.franckyi.gamehooks.api.common.tag.ListTag;
import com.github.franckyi.gamehooks.api.common.tag.Tag;

import java.util.HashMap;
import java.util.Map;

public class FabricCompoundTag implements CompoundTag {
    private final net.minecraft.nbt.CompoundTag tag;

    public FabricCompoundTag() {
        this(new net.minecraft.nbt.CompoundTag());
    }

    public FabricCompoundTag(net.minecraft.nbt.CompoundTag tag) {
        this.tag = tag;
    }

    public FabricCompoundTag(Map<String, Tag> value) {
        this();
        value.forEach((s, tag1) -> tag.put(s, tag1.get()));
    }

    @Override
    public Map<String, Tag> getValue() {
        Map<String, Tag> value = new HashMap<>();
        for (String key : tag.getKeys()) {
            value.put(key, FabricTagFactory.from(tag.get(key)));
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
        return new FabricListTag(tag.getList(key, type));
    }

    @Override
    public CompoundTag getCompound(String key) {
        return new FabricCompoundTag(tag.getCompound(key));
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
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.CompoundTag get() {
        return tag;
    }
}
