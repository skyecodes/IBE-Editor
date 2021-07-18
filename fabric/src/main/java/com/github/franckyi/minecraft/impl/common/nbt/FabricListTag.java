package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.ListTag;
import com.github.franckyi.minecraft.api.common.tag.Tag;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FabricListTag implements ListTag {
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
    public List<Tag> getValue() {
        return tag.stream().map(FabricTagFactory::from).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return tag.size();
    }

    @Override
    public boolean isEmpty() {
        return tag.isEmpty();
    }

    @Override
    public String getString(int index) {
        return tag.getString(index);
    }

    @Override
    public void addString(String value) {
        tag.add(NbtString.of(value));
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtList get() {
        return tag;
    }
}
