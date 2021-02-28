package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.TagFactory;
import com.github.franckyi.gamehooks.api.common.tag.Tag;
import net.minecraft.nbt.*;

public final class ForgeTagFactory implements TagFactory {
    public static final TagFactory INSTANCE = new ForgeTagFactory();

    private ForgeTagFactory() {
    }

    public static Tag from(net.minecraft.nbt.INBT tag) {
        switch (tag.getId()) {
            case Tag.BYTE_ID:
                return new ForgeByteTag(((ByteNBT) tag));
            case Tag.SHORT_ID:
                return new ForgeShortTag((ShortNBT) tag);
            case Tag.INT_ID:
                return new ForgeIntTag((IntNBT) tag);
            case Tag.LONG_ID:
                return new ForgeLongTag((LongNBT) tag);
            case Tag.FLOAT_ID:
                return new ForgeFloatTag((FloatNBT) tag);
            case Tag.DOUBLE_ID:
                return new ForgeDoubleTag((DoubleNBT) tag);
            case Tag.BYTE_ARRAY_ID:
                return new ForgeByteArrayTag((ByteArrayNBT) tag);
            case Tag.STRING_ID:
                return new ForgeStringTag((StringNBT) tag);
            case Tag.LIST_ID:
                return new ForgeListTag((ListNBT) tag);
            case Tag.COMPOUND_ID:
                return new ForgeCompoundTag((CompoundNBT) tag);
            case Tag.INT_ARRAY_ID:
                return new ForgeIntArrayTag((IntArrayNBT) tag);
            case Tag.LONG_ARRAY_ID:
                return new ForgeLongArrayTag((LongArrayNBT) tag);
            default:
                return null;
        }
    }

    @Override
    public Tag create(byte type) {
        switch (type) {
            case Tag.BYTE_ID:
                return new ForgeByteTag();
            case Tag.SHORT_ID:
                return new ForgeShortTag();
            case Tag.INT_ID:
                return new ForgeIntTag();
            case Tag.LONG_ID:
                return new ForgeLongTag();
            case Tag.FLOAT_ID:
                return new ForgeFloatTag();
            case Tag.DOUBLE_ID:
                return new ForgeDoubleTag();
            case Tag.BYTE_ARRAY_ID:
                return new ForgeByteArrayTag();
            case Tag.STRING_ID:
                return new ForgeStringTag();
            case Tag.LIST_ID:
                return new ForgeListTag();
            case Tag.COMPOUND_ID:
                return new ForgeCompoundTag();
            case Tag.INT_ARRAY_ID:
                return new ForgeIntArrayTag();
            case Tag.LONG_ARRAY_ID:
                return new ForgeLongArrayTag();
            default:
                return null;
        }
    }
}
