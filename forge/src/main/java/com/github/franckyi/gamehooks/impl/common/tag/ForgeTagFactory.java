package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.TagFactory;
import com.github.franckyi.gamehooks.api.common.tag.*;
import net.minecraft.nbt.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    public static CompoundTag parseCompound(CompoundNBT compound) {
        return new ForgeCompoundTag(compound);
    }

    @Override
    public Tag createEmptyTag(byte type) {
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

    @Override
    public ByteTag createByteTag(byte value) {
        return new ForgeByteTag(value);
    }

    @Override
    public ShortTag createShortTag(short value) {
        return new ForgeShortTag(value);
    }

    @Override
    public IntTag createIntTag(int value) {
        return new ForgeIntTag(value);
    }

    @Override
    public LongTag createLongTag(long value) {
        return new ForgeLongTag(value);
    }

    @Override
    public FloatTag createFloatTag(float value) {
        return new ForgeFloatTag(value);
    }

    @Override
    public DoubleTag createDoubleTag(double value) {
        return new ForgeDoubleTag(value);
    }

    @Override
    public ByteArrayTag createByteArrayTag(List<Byte> value) {
        return new ForgeByteArrayTag(value);
    }

    @Override
    public StringTag createStringTag(String value) {
        return new ForgeStringTag(value);
    }

    @Override
    public ListTag createListTag(Collection<Tag> value) {
        return new ForgeListTag(value);
    }

    @Override
    public CompoundTag createCompoundTag(Map<String, Tag> value) {
        return new ForgeCompoundTag(value);
    }

    @Override
    public IntArrayTag createIntArrayTag(List<Integer> value) {
        return new ForgeIntArrayTag(value);
    }

    @Override
    public LongArrayTag createLongArrayTag(List<Long> value) {
        return new ForgeLongArrayTag(value);
    }
}
