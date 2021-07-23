package com.github.franckyi.gameadapter.forge.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ListTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ForgeCompoundTag extends AbstractMap<String, Tag> implements CompoundTag {
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
    public Set<Entry<String, Tag>> entrySet() {
        return tag.getAllKeys().stream()
                .map(s -> new CompoundTagEntry(s, ForgeTagFactory.from(tag.get(s))))
                .collect(Collectors.toSet());
    }

    @Override
    public Tag put(String key, Tag value) {
        return ForgeTagFactory.from(tag.put(key, value.get()));
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
    public double getDouble(String key) {
        return tag.getDouble(key);
    }

    @Override
    public UUID getUUID(String key) {
        return tag.getUUID(key);
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
    public void putDouble(String key, double value) {
        tag.putDouble(key, value);
    }

    @Override
    public void putUUID(String key, UUID value) {
        tag.putUUID(key, value);
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
