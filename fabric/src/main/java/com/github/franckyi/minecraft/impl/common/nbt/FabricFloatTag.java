package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.FloatTag;

public class FabricFloatTag implements FloatTag {
    private final net.minecraft.nbt.FloatTag tag;

    public FabricFloatTag() {
        this(0F);
    }

    public FabricFloatTag(float value) {
        this(net.minecraft.nbt.FloatTag.of(value));
    }

    public FabricFloatTag(net.minecraft.nbt.FloatTag tag) {
        this.tag = tag;
    }

    @Override
    public float getValue() {
        return tag.getFloat();
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.FloatTag get() {
        return tag;
    }

    @Override
    public String toString() {
        return Float.toString(getValue());
    }
}
