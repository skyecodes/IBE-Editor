package com.github.franckyi.gameadapter.fabric.common.nbt;

import com.github.franckyi.gameadapter.api.common.tag.*;
import net.minecraft.nbt.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class FabricTagFactory implements TagFactory {
    public static final TagFactory INSTANCE = new FabricTagFactory();

    private FabricTagFactory() {
    }

    public static Tag from(NbtElement tag) {
        if (tag == null) return null;
        switch (tag.getType()) {
            case Tag.BYTE_ID:
                return new FabricByteTag(((NbtByte) tag));
            case Tag.SHORT_ID:
                return new FabricShortTag((NbtShort) tag);
            case Tag.INT_ID:
                return new FabricIntTag((NbtInt) tag);
            case Tag.LONG_ID:
                return new FabricLongTag((NbtLong) tag);
            case Tag.FLOAT_ID:
                return new FabricFloatTag((NbtFloat) tag);
            case Tag.DOUBLE_ID:
                return new FabricDoubleTag((NbtDouble) tag);
            case Tag.BYTE_ARRAY_ID:
                return new FabricByteArrayTag((NbtByteArray) tag);
            case Tag.STRING_ID:
                return new FabricStringTag((NbtString) tag);
            case Tag.LIST_ID:
                return new FabricListTag((NbtList) tag);
            case Tag.COMPOUND_ID:
                return new FabricCompoundTag((NbtCompound) tag);
            case Tag.INT_ARRAY_ID:
                return new FabricIntArrayTag((NbtIntArray) tag);
            case Tag.LONG_ARRAY_ID:
                return new FabricLongArrayTag((NbtLongArray) tag);
            default:
                return null;
        }
    }

    public static CompoundTag parseCompound(NbtCompound tag) {
        return new FabricCompoundTag(tag);
    }

    @Override
    public ByteTag createByteTag() {
        return new FabricByteTag();
    }

    @Override
    public ByteTag createByteTag(byte value) {
        return new FabricByteTag(value);
    }

    @Override
    public ShortTag createShortTag() {
        return new FabricShortTag();
    }

    @Override
    public ShortTag createShortTag(short value) {
        return new FabricShortTag(value);
    }

    @Override
    public IntTag createIntTag() {
        return new FabricIntTag();
    }

    @Override
    public IntTag createIntTag(int value) {
        return new FabricIntTag(value);
    }

    @Override
    public LongTag createLongTag() {
        return new FabricLongTag();
    }

    @Override
    public LongTag createLongTag(long value) {
        return new FabricLongTag(value);
    }

    @Override
    public FloatTag createFloatTag() {
        return new FabricFloatTag();
    }

    @Override
    public FloatTag createFloatTag(float value) {
        return new FabricFloatTag(value);
    }

    @Override
    public DoubleTag createDoubleTag() {
        return new FabricDoubleTag();
    }

    @Override
    public DoubleTag createDoubleTag(double value) {
        return new FabricDoubleTag(value);
    }

    @Override
    public ByteArrayTag createByteArrayTag() {
        return new FabricByteArrayTag();
    }

    @Override
    public ByteArrayTag createByteArrayTag(List<Byte> value) {
        return new FabricByteArrayTag(value);
    }

    @Override
    public StringTag createStringTag() {
        return new FabricStringTag();
    }

    @Override
    public StringTag createStringTag(String value) {
        return new FabricStringTag(value);
    }

    @Override
    public ListTag createListTag() {
        return new FabricListTag();
    }

    @Override
    public ListTag createListTag(Collection<Tag> value) {
        return new FabricListTag(value);
    }

    @Override
    public CompoundTag createCompoundTag() {
        return new FabricCompoundTag();
    }

    @Override
    public CompoundTag createCompoundTag(Map<String, Tag> value) {
        return new FabricCompoundTag(value);
    }

    @Override
    public IntArrayTag createIntArrayTag() {
        return new FabricIntArrayTag();
    }

    @Override
    public IntArrayTag createIntArrayTag(List<Integer> value) {
        return new FabricIntArrayTag(value);
    }

    @Override
    public LongArrayTag createLongArrayTag() {
        return new FabricLongArrayTag();
    }

    @Override
    public LongArrayTag createLongArrayTag(List<Long> value) {
        return new FabricLongArrayTag(value);
    }
}
