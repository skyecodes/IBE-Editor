package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.tag.ListTag;
import com.github.franckyi.minecraft.api.common.tag.Tag;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.Map;

public class FabricCompoundTag implements CompoundTag {
    private final NbtCompound tag;

    public FabricCompoundTag() {
        this(new NbtCompound());
    }

    public FabricCompoundTag(NbtCompound tag) {
        this.tag = tag;
    }

    public FabricCompoundTag(Map<String, Tag> value) {
        this();
        value.forEach((s, tag1) -> tag.put(s, tag1.get()));
    }

    @Override
    public Map<String, Tag> getEntries() {
        Map<String, Tag> value = new HashMap<>();
        for (String key : tag.getKeys()) {
            value.put(key, FabricTagFactory.from(tag.get(key)));
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
        return new FabricListTag(tag.getList(key, type));
    }

    @Override
    public CompoundTag getCompound(String key) {
        return new FabricCompoundTag(tag.getCompound(key));
    }

    @Override
    public CompoundTag getOrCreateCompound(String key) {
        if (!tag.contains(key, COMPOUND_ID)) {
            tag.put(key, new NbtCompound());
        }
        return new FabricCompoundTag(tag.getCompound(key));
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
        return new FabricCompoundTag(tag.copy());
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtCompound get() {
        return tag;
    }
}
