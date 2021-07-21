package com.github.franckyi.gameadapter.fabric.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.LongArrayTag;
import net.minecraft.nbt.NbtLongArray;

import java.util.List;

public class FabricLongArrayTag implements LongArrayTag {
    private final NbtLongArray tag;

    public FabricLongArrayTag() {
        this(new long[0]);
    }

    public FabricLongArrayTag(long[] value) {
        this(new NbtLongArray(value));
    }

    public FabricLongArrayTag(List<Long> value) {
        this(new NbtLongArray(value));
    }

    public FabricLongArrayTag(NbtLongArray tag) {
        this.tag = tag;
    }

    @Override
    public long[] getValue() {
        return tag.getLongArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtLongArray get() {
        return tag;
    }
}
