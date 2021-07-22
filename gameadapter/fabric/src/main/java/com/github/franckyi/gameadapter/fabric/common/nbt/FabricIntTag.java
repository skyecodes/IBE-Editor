package com.github.franckyi.gameadapter.fabric.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.IntTag;
import net.minecraft.nbt.NbtInt;

public class FabricIntTag implements IntTag {
    private final NbtInt tag;

    public FabricIntTag() {
        this(0);
    }

    public FabricIntTag(int value) {
        this(NbtInt.of(value));
    }

    public FabricIntTag(NbtInt tag) {
        this.tag = tag;
    }

    @Override
    public int getValue() {
        return tag.intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtInt get() {
        return tag;
    }

    @Override
    public String toString() {
        return Integer.toString(getValue());
    }
}
