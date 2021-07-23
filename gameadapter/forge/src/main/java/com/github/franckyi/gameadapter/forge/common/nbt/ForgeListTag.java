package com.github.franckyi.gameadapter.forge.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ListTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;

import java.util.AbstractList;
import java.util.Collection;

public class ForgeListTag extends AbstractList<Tag> implements ListTag {
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

    @Override
    public int size() {
        return tag.size();
    }

    @Override
    public Tag get(int index) {
        return ForgeTagFactory.from(tag.get(index));
    }

    @Override
    public Tag set(int index, Tag element) {
        return ForgeTagFactory.from(tag.set(index, element.get()));
    }

    @Override
    public void add(int index, Tag element) {
        tag.add(index, element.get());
    }

    @Override
    public Tag remove(int index) {
        return ForgeTagFactory.from(tag.remove(index));
    }

    @Override
    public String getString(int index) {
        return tag.getString(index);
    }

    @Override
    public CompoundTag getCompound(int index) {
        return new ForgeCompoundTag(tag.getCompound(index));
    }

    @Override
    public void addString(String value) {
        tag.add(StringNBT.valueOf(value));
    }

    @Override
    public void addTag(Tag tag) {
        this.tag.add(tag.get());
    }

    @Override
    @SuppressWarnings("unchecked")
    public ListNBT get() {
        return tag;
    }
}
