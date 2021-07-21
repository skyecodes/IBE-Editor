package com.github.franckyi.gameadapter.fabric.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.ShortTag;
import net.minecraft.nbt.NbtShort;

public class FabricShortTag implements ShortTag {
    private final NbtShort tag;

    public FabricShortTag() {
        this((short) 0);
    }

    public FabricShortTag(short value) {
        this(NbtShort.of(value));
    }

    public FabricShortTag(NbtShort tag) {
        this.tag = tag;
    }

    @Override
    public short getValue() {
        return tag.shortValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtShort get() {
        return tag;
    }

    @Override
    public String toString() {
        return Short.toString(getValue());
    }
}
