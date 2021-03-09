package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.ByteArrayTag;

import java.util.List;

public class FabricByteArrayTag implements ByteArrayTag {
    private final net.minecraft.nbt.ByteArrayTag tag;

    public FabricByteArrayTag() {
        this (new byte[0]);
    }

    public FabricByteArrayTag(List<Byte> value) {
        this(new net.minecraft.nbt.ByteArrayTag(value));
    }

    public FabricByteArrayTag(byte[] value) {
        this(new net.minecraft.nbt.ByteArrayTag(value));
    }

    public FabricByteArrayTag(net.minecraft.nbt.ByteArrayTag tag) {
        this.tag = tag;
    }

    @Override
    public byte[] getValue() {
        return tag.getByteArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.ByteArrayTag get() {
        return tag;
    }
}
