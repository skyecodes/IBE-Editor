package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.IntArrayTag;
import net.minecraft.nbt.NbtIntArray;

import java.util.List;

public class FabricIntArrayTag implements IntArrayTag {
    private final NbtIntArray tag;

    public FabricIntArrayTag() {
        this(new int[0]);
    }

    public FabricIntArrayTag(int[] value) {
        this(new NbtIntArray(value));
    }

    public FabricIntArrayTag(List<Integer> value) {
        this(new NbtIntArray(value));
    }

    public FabricIntArrayTag(NbtIntArray tag) {
        this.tag = tag;
    }

    @Override
    public int[] getValue() {
        return tag.getIntArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtIntArray get() {
        return tag;
    }
}
