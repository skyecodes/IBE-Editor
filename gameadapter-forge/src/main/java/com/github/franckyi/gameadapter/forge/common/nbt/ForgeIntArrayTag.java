package com.github.franckyi.gameadapter.forge.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.IntArrayTag;
import net.minecraft.nbt.IntArrayNBT;

import java.util.List;

public class ForgeIntArrayTag implements IntArrayTag {
    private final IntArrayNBT tag;

    public ForgeIntArrayTag() {
        this(new int[0]);
    }

    public ForgeIntArrayTag(int[] value) {
        this(new IntArrayNBT(value));
    }

    public ForgeIntArrayTag(List<Integer> value) {
        this(new IntArrayNBT(value));
    }

    public ForgeIntArrayTag(IntArrayNBT tag) {
        this.tag = tag;
    }

    @Override
    public int[] getValue() {
        return tag.getAsIntArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public IntArrayNBT get() {
        return tag;
    }
}
