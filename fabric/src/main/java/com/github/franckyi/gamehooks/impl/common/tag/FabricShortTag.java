package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.tag.ShortTag;

public class FabricShortTag implements ShortTag {
    private final net.minecraft.nbt.ShortTag tag;

    public FabricShortTag() {
        this((short) 0);
    }

    public FabricShortTag(short value) {
        this(net.minecraft.nbt.ShortTag.of(value));
    }

    public FabricShortTag(net.minecraft.nbt.ShortTag tag) {
        this.tag = tag;
    }

    @Override
    public short getValue() {
        return tag.getShort();
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.nbt.ShortTag get() {
        return tag;
    }

    @Override
    public String toString() {
        return Short.toString(getValue());
    }
}
