package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.github.franckyi.gamehooks.api.common.tag.ListTag;
import com.github.franckyi.gamehooks.api.common.tag.Tag;
import net.minecraft.nbt.ListNBT;

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

    @Override
    public List<Tag> getValue() {
        return tag.stream().map(ForgeTagFactory::from).collect(Collectors.toList());
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
        return new ForgeListTag(tag.getList(index));
    }

    @Override
    public CompoundTag getCompound(int index) {
        return new ForgeCompoundTag(tag.getCompound(index));
    }

    @Override
    public int[] getIntArray(int index) {
        return tag.getIntArray(index);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ListNBT get() {
        return tag;
    }
}
