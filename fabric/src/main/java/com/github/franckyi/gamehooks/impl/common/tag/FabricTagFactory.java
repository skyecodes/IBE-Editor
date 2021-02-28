package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.TagFactory;
import com.github.franckyi.gamehooks.api.common.tag.Tag;
import net.minecraft.nbt.*;

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

    @Override
    public Tag create(byte type) {
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
}
