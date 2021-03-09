package com.github.franckyi.minecraft.impl.common.nbt;

import com.github.franckyi.minecraft.api.common.tag.Tag;
import com.github.franckyi.minecraft.api.common.tag.TagFactory;
import net.minecraft.nbt.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class FabricTagFactory implements TagFactory {
    public static final TagFactory INSTANCE = new FabricTagFactory();

    private FabricTagFactory() {
    }

    public static Tag from(net.minecraft.nbt.Tag tag) {
        switch (tag.getType()) {
            case Tag.BYTE_ID:
                return new FabricByteTag(((ByteTag) tag));
            case Tag.SHORT_ID:
                return new FabricShortTag((ShortTag) tag);
            case Tag.INT_ID:
                return new FabricIntTag((IntTag) tag);
            case Tag.LONG_ID:
                return new FabricLongTag((LongTag) tag);
            case Tag.FLOAT_ID:
                return new FabricFloatTag((FloatTag) tag);
            case Tag.DOUBLE_ID:
                return new FabricDoubleTag((DoubleTag) tag);
            case Tag.BYTE_ARRAY_ID:
                return new FabricByteArrayTag((ByteArrayTag) tag);
            case Tag.STRING_ID:
                return new FabricStringTag((StringTag) tag);
            case Tag.LIST_ID:
                return new FabricListTag((ListTag) tag);
            case Tag.COMPOUND_ID:
                return new FabricCompoundTag((CompoundTag) tag);
            case Tag.INT_ARRAY_ID:
                return new FabricIntArrayTag((IntArrayTag) tag);
            case Tag.LONG_ARRAY_ID:
                return new FabricLongArrayTag((LongArrayTag) tag);
            default:
                return null;
        }
    }

    public static com.github.franckyi.minecraft.api.common.tag.CompoundTag parseCompound(CompoundTag tag) {
        return new FabricCompoundTag(tag);
    }

    @Override
    public Tag createEmptyTag(byte type) {
        switch (type) {
            case Tag.BYTE_ID:
                return new FabricByteTag();
            case Tag.SHORT_ID:
                return new FabricShortTag();
            case Tag.INT_ID:
                return new FabricIntTag();
            case Tag.LONG_ID:
                return new FabricLongTag();
            case Tag.FLOAT_ID:
                return new FabricFloatTag();
            case Tag.DOUBLE_ID:
                return new FabricDoubleTag();
            case Tag.BYTE_ARRAY_ID:
                return new FabricByteArrayTag();
            case Tag.STRING_ID:
                return new FabricStringTag();
            case Tag.LIST_ID:
                return new FabricListTag();
            case Tag.COMPOUND_ID:
                return new FabricCompoundTag();
            case Tag.INT_ARRAY_ID:
                return new FabricIntArrayTag();
            case Tag.LONG_ARRAY_ID:
                return new FabricLongArrayTag();
            default:
                return null;
        }
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.ByteTag createByteTag(byte value) {
        return new FabricByteTag(value);
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.ShortTag createShortTag(short value) {
        return new FabricShortTag(value);
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.IntTag createIntTag(int value) {
        return new FabricIntTag(value);
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.LongTag createLongTag(long value) {
        return new FabricLongTag(value);
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.FloatTag createFloatTag(float value) {
        return new FabricFloatTag(value);
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.DoubleTag createDoubleTag(double value) {
        return new FabricDoubleTag(value);
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.ByteArrayTag createByteArrayTag(List<Byte> value) {
        return new FabricByteArrayTag(value);
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.StringTag createStringTag(String value) {
        return new FabricStringTag(value);
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.ListTag createListTag(Collection<Tag> value) {
        return new FabricListTag(value);
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.CompoundTag createCompoundTag(Map<String, Tag> value) {
        return new FabricCompoundTag(value);
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.IntArrayTag createIntArrayTag(List<Integer> value) {
        return new FabricIntArrayTag(value);
    }

    @Override
    public com.github.franckyi.minecraft.api.common.tag.LongArrayTag createLongArrayTag(List<Long> value) {
        return new FabricLongArrayTag(value);
    }
}
