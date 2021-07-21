package com.github.franckyi.gameadapter.fabric.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.DoubleTag;
import net.minecraft.nbt.NbtDouble;

public class FabricDoubleTag implements DoubleTag {
    private final NbtDouble tag;

    public FabricDoubleTag() {
        this(0.);
    }

    public FabricDoubleTag(double value) {
        this(NbtDouble.of(value));
    }

    public FabricDoubleTag(NbtDouble tag) {
        this.tag = tag;
    }

    @Override
    public double getValue() {
        return tag.doubleValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtDouble get() {
        return tag;
    }

    @Override
    public String toString() {
        return Double.toString(getValue());
    }
}
