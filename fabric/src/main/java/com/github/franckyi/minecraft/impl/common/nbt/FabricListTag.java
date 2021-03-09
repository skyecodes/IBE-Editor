package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.tag.ListTag;
import com.github.franckyi.minecraft.api.common.tag.Tag;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FabricListTag implements ListTag {
    private final net.minecraft.nbt.ListTag tag;

    public FabricListTag() {
        this(new net.minecraft.nbt.ListTag());
    }

    public FabricListTag(net.minecraft.nbt.ListTag tag) {
        this.tag = tag;
    }

    public FabricListTag(Collection<Tag> value) {
        this();
        value.forEach(tag1 -> tag.add(tag1.get()));
    }

    @Override
    public List<Tag> getValue() {
        return tag.stream().map(FabricTagFactory::from).collect(Collectors.toList());
    }

    @Override
    public short getShort(int index) {
        return tag.getShort(index);
    }

    @Override
    public int getInt(int index) {
        return tag.getInt(index);
    }

    @Override
    public float getFloat(int index) {
        return tag.getFloat(index);
    }

    @Override
    public double getDouble(int index) {
        return tag.getDouble(index);
    }

    @Override
    public String getString(int index) {
        return tag.getString(index);
    }

    @Override
    public ListTag getList(int index) {
        return new FabricListTag(tag.getList(index));
    }

    @Override
    public CompoundTag getCompound(int index) {
        return new FabricCompoundTag(tag.getCompound(index));
    }

    @Override
    public int[] getIntArray(int index) {
        return tag.getIntArray(index);
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.ListTag get() {
        return tag;
    }
}
