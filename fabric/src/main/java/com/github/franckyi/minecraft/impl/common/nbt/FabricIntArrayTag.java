package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.IntArrayTag;

import java.util.List;

public class FabricIntArrayTag implements IntArrayTag {
    private final net.minecraft.nbt.IntArrayTag tag;

    public FabricIntArrayTag() {
        this(new int[0]);
    }

    public FabricIntArrayTag(int[] value) {
        this(new net.minecraft.nbt.IntArrayTag(value));
    }

    public FabricIntArrayTag(List<Integer> value) {
        this(new net.minecraft.nbt.IntArrayTag(value));
    }

    public FabricIntArrayTag(net.minecraft.nbt.IntArrayTag tag) {
        this.tag = tag;
    }

    @Override
    public int[] getValue() {
        return tag.getIntArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.IntArrayTag get() {
        return tag;
    }
}
