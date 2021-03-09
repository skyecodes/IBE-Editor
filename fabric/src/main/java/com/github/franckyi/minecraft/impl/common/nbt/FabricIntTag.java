package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.IntTag;

public class FabricIntTag implements IntTag {
    private final net.minecraft.nbt.IntTag tag;

    public FabricIntTag() {
        this(0);
    }

    public FabricIntTag(int value) {
        this(net.minecraft.nbt.IntTag.of(value));
    }

    public FabricIntTag(net.minecraft.nbt.IntTag tag) {
        this.tag = tag;
    }

    @Override
    public int getValue() {
        return tag.getInt();
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.IntTag get() {
        return tag;
    }

    @Override
    public String toString() {
        return Integer.toString(getValue());
    }
}
