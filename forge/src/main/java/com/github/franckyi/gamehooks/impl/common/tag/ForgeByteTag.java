package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.tag.ByteTag;
import net.minecraft.nbt.ByteNBT;

public class ForgeByteTag implements ByteTag {
    private final ByteNBT tag;

    public ForgeByteTag() {
        this((byte) 0);
    }

    public ForgeByteTag(byte value) {
        this(ByteNBT.valueOf(value));
    }

    public ForgeByteTag(ByteNBT tag) {
        this.tag = tag;
    }

    @Override
    public byte getValue() {
        return tag.getByte();
    }

    @Override
    @SuppressWarnings("unchecked")
    public ByteNBT get() {
        return tag;
    }

    @Override
    public String toString() {
        return Byte.toString(getValue());
    }
}
