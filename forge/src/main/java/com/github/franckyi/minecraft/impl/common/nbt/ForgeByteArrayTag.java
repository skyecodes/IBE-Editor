package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.ByteArrayTag;
import net.minecraft.nbt.ByteArrayNBT;

import java.util.List;

public class ForgeByteArrayTag implements ByteArrayTag {
    private final ByteArrayNBT tag;

    public ForgeByteArrayTag() {
        this (new byte[0]);
    }

    public ForgeByteArrayTag(List<Byte> value) {
        this(new ByteArrayNBT(value));
    }

    public ForgeByteArrayTag(byte[] value) {
        this(new ByteArrayNBT(value));
    }

    public ForgeByteArrayTag(ByteArrayNBT tag) {
        this.tag = tag;
    }

    @Override
    public byte[] getValue() {
        return tag.getByteArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public ByteArrayNBT get() {
        return tag;
    }
}
