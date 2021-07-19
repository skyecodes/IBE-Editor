package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.StringTag;
import net.minecraft.nbt.StringNBT;

public class ForgeStringTag implements StringTag {
    private final StringNBT tag;

    public ForgeStringTag() {
        this("");
    }

    public ForgeStringTag(String value) {
        this(StringNBT.valueOf(value));
    }

    public ForgeStringTag(StringNBT tag) {
        this.tag = tag;
    }

    @Override
    public String getValue() {
        return tag.getAsString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public StringNBT get() {
        return tag;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
