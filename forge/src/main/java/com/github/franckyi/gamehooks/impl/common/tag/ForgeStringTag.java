package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.tag.StringTag;
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
        return tag.getString();
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
