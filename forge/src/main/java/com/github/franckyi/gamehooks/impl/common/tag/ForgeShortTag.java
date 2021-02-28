package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.tag.ShortTag;
import net.minecraft.nbt.ShortNBT;

public class ForgeShortTag implements ShortTag {
    private final ShortNBT tag;

    public ForgeShortTag() {
        this((short) 0);
    }

    public ForgeShortTag(short value) {
        this(ShortNBT.valueOf(value));
    }

    public ForgeShortTag(ShortNBT tag) {
        this.tag = tag;
    }

    @Override
    public short getValue() {
        return tag.getShort();
    }

    @Override
    @SuppressWarnings("unchecked")
    public ShortNBT get() {
        return tag;
    }

    @Override
    public String toString() {
        return Short.toString(getValue());
    }
}
