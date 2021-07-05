package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.ByteTag;
import net.minecraft.nbt.NbtByte;

public class FabricByteTag implements ByteTag {
    private final NbtByte tag;

    public FabricByteTag() {
        this((byte) 0);
    }

    public FabricByteTag(byte value) {
        this(NbtByte.of(value));
    }

    public FabricByteTag(NbtByte tag) {
        this.tag = tag;
    }

    @Override
    public byte getValue() {
        return tag.byteValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtByte get() {
        return tag;
    }

    @Override
    public String toString() {
        return Byte.toString(getValue());
    }
}
