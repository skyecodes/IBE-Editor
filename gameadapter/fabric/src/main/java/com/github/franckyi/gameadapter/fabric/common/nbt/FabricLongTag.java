package com.github.franckyi.gameadapter.fabric.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.LongTag;
import net.minecraft.nbt.NbtLong;

public class FabricLongTag implements LongTag {
    private final NbtLong tag;

    public FabricLongTag() {
        this(0L);
    }

    public FabricLongTag(long value) {
        this(NbtLong.of(value));
    }

    public FabricLongTag(NbtLong tag) {
        this.tag = tag;
    }

    @Override
    public long getValue() {
        return tag.longValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtLong get() {
        return tag;
    }

    @Override
    public String toString() {
        return Long.toString(getValue());
    }
}
