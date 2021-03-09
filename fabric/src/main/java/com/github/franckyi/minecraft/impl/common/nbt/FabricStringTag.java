package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.StringTag;

public class FabricStringTag implements StringTag {
    private final net.minecraft.nbt.StringTag tag;

    public FabricStringTag() {
        this("");
    }

    public FabricStringTag(String value) {
        this(net.minecraft.nbt.StringTag.of(value));
    }

    public FabricStringTag(net.minecraft.nbt.StringTag tag) {
        this.tag = tag;
    }

    @Override
    public String getValue() {
        return tag.asString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.StringTag get() {
        return tag;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
