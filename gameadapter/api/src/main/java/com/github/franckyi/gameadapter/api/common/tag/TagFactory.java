package com.github.franckyi.gameadapter.api.common.tag;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TagFactory {
    default ITag createEmptyTag(byte type) {
        switch (type) {
            case ITag.BYTE_ID:
                return createByteTag();
            case ITag.SHORT_ID:
                return createShortTag();
            case ITag.INT_ID:
                return createIntTag();
            case ITag.LONG_ID:
                return createLongTag();
            case ITag.FLOAT_ID:
                return createFloatTag();
            case ITag.DOUBLE_ID:
                return createDoubleTag();
            case ITag.BYTE_ARRAY_ID:
                return createByteArrayTag();
            case ITag.STRING_ID:
                return createStringTag();
            case ITag.LIST_ID:
                return createListTag();
            case ITag.COMPOUND_ID:
                return createCompoundTag();
            case ITag.INT_ARRAY_ID:
                return createIntArrayTag();
            case ITag.LONG_ARRAY_ID:
                return createLongArrayTag();
            default:
                return null;
        }
    }

    default IByteTag createByteTag() {
        return createByteTag((byte) 0);
    }

    IByteTag createByteTag(byte value);

    default IShortTag createShortTag() {
        return createShortTag((short) 0);
    }

    IShortTag createShortTag(short value);

    default IIntTag createIntTag() {
        return createIntTag(0);
    }

    IIntTag createIntTag(int value);

    default ILongTag createLongTag() {
        return createLongTag(0);
    }

    ILongTag createLongTag(long value);

    default IFloatTag createFloatTag() {
        return createFloatTag(0);
    }

    IFloatTag createFloatTag(float value);

    default IDoubleTag createDoubleTag() {
        return createDoubleTag(0);
    }

    IDoubleTag createDoubleTag(double value);

    IByteArrayTag createByteArrayTag();

    IByteArrayTag createByteArrayTag(List<Byte> value);

    default IStringTag createStringTag() {
        return createStringTag("");
    }

    IStringTag createStringTag(String value);

    IListTag createListTag();

    IListTag createListTag(Collection<ITag> value);

    ICompoundTag createCompoundTag();

    ICompoundTag createCompoundTag(Map<String, ITag> value);

    ICompoundTag parseCompoundTag(String snbt);

    IIntArrayTag createIntArrayTag();

    IIntArrayTag createIntArrayTag(List<Integer> value);

    ILongArrayTag createLongArrayTag();

    ILongArrayTag createLongArrayTag(List<Long> value);
}
