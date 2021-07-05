package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.FloatTag;
import net.minecraft.nbt.NbtFloat;

public class FabricFloatTag implements FloatTag {
    private final NbtFloat tag;

    public FabricFloatTag() {
        this(0F);
    }

    public FabricFloatTag(float value) {
        this(NbtFloat.of(value));
    }

    public FabricFloatTag(NbtFloat tag) {
        this.tag = tag;
    }

    @Override
    public float getValue() {
        return tag.floatValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtFloat get() {
        return tag;
    }

    @Override
    public String toString() {
        return Float.toString(getValue());
    }
}
