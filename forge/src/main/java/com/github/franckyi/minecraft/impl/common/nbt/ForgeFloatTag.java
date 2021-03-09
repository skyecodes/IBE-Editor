package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.FloatTag;
import net.minecraft.nbt.FloatNBT;

public class ForgeFloatTag implements FloatTag {
    private final FloatNBT tag;

    public ForgeFloatTag() {
        this(0F);
    }

    public ForgeFloatTag(float value) {
        this(FloatNBT.valueOf(value));
    }

    public ForgeFloatTag(FloatNBT tag) {
        this.tag = tag;
    }

    @Override
    public float getValue() {
        return tag.getFloat();
    }

    @Override
    @SuppressWarnings("unchecked")
    public FloatNBT get() {
        return tag;
    }

    @Override
    public String toString() {
        return Float.toString(getValue());
    }
}
