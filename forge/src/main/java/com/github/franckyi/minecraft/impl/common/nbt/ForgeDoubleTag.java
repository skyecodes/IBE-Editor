package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.DoubleTag;
import net.minecraft.nbt.DoubleNBT;

public class ForgeDoubleTag implements DoubleTag {
    private final DoubleNBT tag;

    public ForgeDoubleTag() {
        this(0.);
    }

    public ForgeDoubleTag(double value) {
        this(DoubleNBT.valueOf(value));
    }

    public ForgeDoubleTag(DoubleNBT tag) {
        this.tag = tag;
    }

    @Override
    public double getValue() {
        return tag.getDouble();
    }

    @Override
    @SuppressWarnings("unchecked")
    public DoubleNBT get() {
        return tag;
    }

    @Override
    public String toString() {
        return Double.toString(getValue());
    }
}
