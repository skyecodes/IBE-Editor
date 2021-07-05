package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.StringTag;
import net.minecraft.nbt.NbtString;

public class FabricStringTag implements StringTag {
    private final NbtString tag;

    public FabricStringTag() {
        this("");
    }

    public FabricStringTag(String value) {
        this(NbtString.of(value));
    }

    public FabricStringTag(NbtString tag) {
        this.tag = tag;
    }

    @Override
    public String getValue() {
        return tag.asString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtString get() {
        return tag;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
