package com.github.franckyi.gameadapter.forge.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ListTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

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
    public boolean getBoolean(String key) {
        return tag.getBoolean(key);
    }

    @Override
    public ListTag getList(String key, byte type) {
        return new ForgeListTag(tag.getList(key, type));
    }

    @Override
    public ListTag getOrCreateList(String key, byte type) {
        if (!tag.contains(key, LIST_ID)) {
            tag.put(key, new ListNBT());
        }
        return getList(key, type);
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
        return getCompound(key);
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
    public void putBoolean(String key, boolean value) {
        tag.putBoolean(key, value);
    }

    @Override
    public void putTag(String key, Tag value) {
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

    @Override
    public String toString() {
        return tag.toString();
    }
}
