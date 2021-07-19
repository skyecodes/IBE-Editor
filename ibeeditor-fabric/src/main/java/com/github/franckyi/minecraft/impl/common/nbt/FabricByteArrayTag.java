package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.ByteArrayTag;
import net.minecraft.nbt.NbtByteArray;

import java.util.List;

public class FabricByteArrayTag implements ByteArrayTag {
    private final NbtByteArray tag;

    public FabricByteArrayTag() {
        this (new byte[0]);
    }

    public FabricByteArrayTag(List<Byte> value) {
        this(new NbtByteArray(value));
    }

    public FabricByteArrayTag(byte[] value) {
        this(new NbtByteArray(value));
    }

    public FabricByteArrayTag(NbtByteArray tag) {
        this.tag = tag;
    }

    @Override
    public byte[] getValue() {
        return tag.getByteArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public NbtByteArray get() {
        return tag;
    }
}
