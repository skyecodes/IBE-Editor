package com.github.franckyi.minecraft.api.common.tag;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TagFactory {
    Tag createEmptyTag(byte type);

    ByteTag createByteTag(byte value);

    ShortTag createShortTag(short value);

    IntTag createIntTag(int value);

    LongTag createLongTag(long value);

    FloatTag createFloatTag(float value);

    DoubleTag createDoubleTag(double value);

    ByteArrayTag createByteArrayTag(List<Byte> value);

    StringTag createStringTag(String value);

    ListTag createListTag(Collection<Tag> value);

    CompoundTag createCompoundTag(Map<String, Tag> value);

    IntArrayTag createIntArrayTag(List<Integer> value);

    LongArrayTag createLongArrayTag(List<Long> value);
}
