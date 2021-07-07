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
    public Map<String, Tag> getEntries() {
        Map<String, Tag> value = new HashMap<>();
        for (String key : tag.getAllKeys()) {
            value.put(key, ForgeTagFactory.from(tag.get(key)));
        }
        return value;
    }

    @Override
    public int getInt(String key) {
        return tag.getInt(key);
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
    public CompoundTag getOrCreateCompound(String key) {
        if (!tag.contains(key, COMPOUND_ID)) {
            tag.put(key, new CompoundNBT());
        }
        return new ForgeCompoundTag(tag.getCompound(key));
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
    public void putCompound(String key, CompoundTag value) {
        tag.put(key, value.get());
    }

    @Override
    public boolean contains(String key, byte type) {
        return tag.contains(key, type);
    }

    @Override
    public void remove(String key) {
        tag.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return tag.isEmpty();
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
