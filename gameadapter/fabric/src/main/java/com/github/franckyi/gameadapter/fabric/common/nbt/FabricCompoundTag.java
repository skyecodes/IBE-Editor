package com.github.franckyi.gameadapter.fabric.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ListTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class FabricCompoundTag extends AbstractMap<String, Tag> implements CompoundTag {
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

    @NotNull
    @Override
    public Set<Entry<String, Tag>> entrySet() {
        return tag.getKeys().stream()
                .map(s -> new CompoundTagEntry(s, FabricTagFactory.from(tag.get(s))))
                .collect(Collectors.toSet());
    }

    @Override
    public Tag put(String key, Tag value) {
        return FabricTagFactory.from(tag.put(key, value.get()));
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
        return tag.getUuid(key);
    }

    @Override
    public ListTag getList(String key, byte type) {
        return new FabricListTag(tag.getList(key, type));
    }

    @Override
    public ListTag getOrCreateList(String key, byte type) {
        if (!tag.contains(key, LIST_ID)) {
            tag.put(key, new NbtList());
        }
        return getList(key, type);
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
        tag.putUuid(key, value);
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
        return new FabricCompoundTag(tag.copy());
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtCompound get() {
        return tag;
    }

    @Override
    public String toString() {
        return tag.toString();
    }
}
