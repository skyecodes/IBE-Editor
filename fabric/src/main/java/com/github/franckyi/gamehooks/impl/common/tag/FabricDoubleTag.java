package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.tag.DoubleTag;

public class FabricDoubleTag implements DoubleTag {
    private final net.minecraft.nbt.DoubleTag tag;

    public FabricDoubleTag() {
        this(0.);
    }

    public FabricDoubleTag(double value) {
        this(net.minecraft.nbt.DoubleTag.of(value));
    }

    public FabricDoubleTag(net.minecraft.nbt.DoubleTag tag) {
        this.tag = tag;
    }

    @Override
    public double getValue() {
        return tag.getDouble();
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.DoubleTag get() {
        return tag;
    }

    @Override
    public String toString() {
        return Double.toString(getValue());
    }
}
