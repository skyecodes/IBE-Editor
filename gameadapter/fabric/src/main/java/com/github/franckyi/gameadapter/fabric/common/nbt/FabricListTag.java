package com.github.franckyi.gameadapter.fabric.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ListTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

import java.util.AbstractList;
import java.util.Collection;

public class FabricListTag extends AbstractList<Tag> implements ListTag {
    private final NbtList tag;

    public FabricListTag() {
        this(new NbtList());
    }

    public FabricListTag(NbtList tag) {
        this.tag = tag;
    }

    public FabricListTag(Collection<Tag> value) {
        this();
        value.forEach(tag1 -> tag.add(tag1.get()));
    }

    @Override
    public Tag get(int index) {
        return FabricTagFactory.from(tag.get(index));
    }

    @Override
    public int size() {
        return tag.size();
    }

    @Override
    public Tag set(int index, Tag element) {
        return FabricTagFactory.from(tag.set(index, element.get()));
    }

    @Override
    public void add(int index, Tag element) {
        tag.add(index, element.get());
    }

    @Override
    public Tag remove(int index) {
        return FabricTagFactory.from(tag.remove(index));
    }

    @Override
    public String getString(int index) {
        return tag.getString(index);
    }

    @Override
    public CompoundTag getCompound(int index) {
        return new FabricCompoundTag(tag.getCompound(index));
    }

    @Override
    public void addString(String value) {
        tag.add(NbtString.of(value));
    }

    @Override
    public void addTag(Tag tag) {
        this.tag.add(tag.get());
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtList get() {
        return tag;
    }
}
