package com.github.franckyi.gameadapter.forge.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.ByteArrayTag;
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
        return tag.getAsByteArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public ByteArrayNBT get() {
        return tag;
    }
}
