package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.ListTag;
import com.github.franckyi.minecraft.api.common.tag.Tag;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ForgeListTag implements ListTag {
    private final ListNBT tag;

    public ForgeListTag() {
        this(new ListNBT());
    }

    public ForgeListTag(ListNBT tag) {
        this.tag = tag;
    }

    public ForgeListTag(Collection<Tag> value) {
        this();
        value.forEach(tag1 -> tag.add(tag1.get()));
    }

    public List<Tag> getValue() {
        return tag.stream().map(ForgeTagFactory::from).collect(Collectors.toList());
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
        tag.add(StringNBT.valueOf(value));
    }

    @Override
    @SuppressWarnings("unchecked")
    public ListNBT get() {
        return tag;
    }
}
