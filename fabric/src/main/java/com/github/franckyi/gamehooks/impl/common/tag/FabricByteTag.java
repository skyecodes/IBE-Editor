package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.tag.ByteTag;

public class FabricByteTag implements ByteTag {
    private final net.minecraft.nbt.ByteTag tag;

    public FabricByteTag() {
        this((byte) 0);
    }

    public FabricByteTag(byte value) {
        this(net.minecraft.nbt.ByteTag.of(value));
    }

    public FabricByteTag(net.minecraft.nbt.ByteTag tag) {
        this.tag = tag;
    }

    @Override
    public byte getValue() {
        return tag.getByte();
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.ByteTag get() {
        return tag;
    }

    @Override
    public String toString() {
        return Byte.toString(getValue());
    }
}
