package com.github.franckyi.gameadapter.api.common.tag;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TagFactory {
    default Tag createEmptyTag(byte type) {
        switch (type) {
            case Tag.BYTE_ID:
                return createByteTag();
            case Tag.SHORT_ID:
                return createShortTag();
            case Tag.INT_ID:
                return createIntTag();
            case Tag.LONG_ID:
                return createLongTag();
            case Tag.FLOAT_ID:
                return createFloatTag();
            case Tag.DOUBLE_ID:
                return createDoubleTag();
            case Tag.BYTE_ARRAY_ID:
                return createByteArrayTag();
            case Tag.STRING_ID:
                return createStringTag();
            case Tag.LIST_ID:
                return createListTag();
            case Tag.COMPOUND_ID:
                return createCompoundTag();
            case Tag.INT_ARRAY_ID:
                return createIntArrayTag();
            case Tag.LONG_ARRAY_ID:
                return createLongArrayTag();
            default:
                return null;
        }
    }

    ByteTag createByteTag();

    ByteTag createByteTag(byte value);

    ShortTag createShortTag();

    ShortTag createShortTag(short value);

    IntTag createIntTag();

    IntTag createIntTag(int value);

    LongTag createLongTag();

    LongTag createLongTag(long value);

    FloatTag createFloatTag();

    FloatTag createFloatTag(float value);

    DoubleTag createDoubleTag();

    DoubleTag createDoubleTag(double value);

    ByteArrayTag createByteArrayTag();

    ByteArrayTag createByteArrayTag(List<Byte> value);

    StringTag createStringTag();

    StringTag createStringTag(String value);

    ListTag createListTag();

    ListTag createListTag(Collection<Tag> value);

    CompoundTag createCompoundTag();

    CompoundTag createCompoundTag(Map<String, Tag> value);

    IntArrayTag createIntArrayTag();

    IntArrayTag createIntArrayTag(List<Integer> value);

    LongArrayTag createLongArrayTag();

    LongArrayTag createLongArrayTag(List<Long> value);
}
