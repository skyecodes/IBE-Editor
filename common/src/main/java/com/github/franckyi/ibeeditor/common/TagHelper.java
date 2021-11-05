package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.guapi.api.Color;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public final class TagHelper {
    private TagHelper() {
    }

    public static final byte BYTE_ID = 1;
    public static final byte SHORT_ID = 2;
    public static final byte INT_ID = 3;
    public static final byte LONG_ID = 4;
    public static final byte FLOAT_ID = 5;
    public static final byte DOUBLE_ID = 6;
    public static final byte BYTE_ARRAY_ID = 7;
    public static final byte STRING_ID = 8;
    public static final byte LIST_ID = 9;
    public static final byte COMPOUND_ID = 10;
    public static final byte INT_ARRAY_ID = 11;
    public static final byte LONG_ARRAY_ID = 12;

    public static Tag createEmptyTag(byte type) {
        switch (type) {
            case BYTE_ID:
                return ByteTag.ZERO;
            case SHORT_ID:
                return ShortTag.valueOf((short) 0);
            case INT_ID:
                return IntTag.valueOf(0);
            case LONG_ID:
                return LongTag.valueOf(0);
            case FLOAT_ID:
                return FloatTag.ZERO;
            case DOUBLE_ID:
                return DoubleTag.ZERO;
            case BYTE_ARRAY_ID:
                return new ByteArrayTag(new byte[0]);
            case STRING_ID:
                return StringTag.valueOf("");
            case LIST_ID:
                return new ListTag();
            case COMPOUND_ID:
                return new CompoundTag();
            case INT_ARRAY_ID:
                return new IntArrayTag(new int[0]);
            case LONG_ARRAY_ID:
                return new LongArrayTag(new long[0]);
            default:
                return null;
        }
    }

    public static ItemStack createPotionItemWithColor(ResourceLocation id, int color) {
        CompoundTag data = new CompoundTag();
        CompoundTag tag = new CompoundTag();
        tag.putString("Potion", id == null ? "minecraft:empty" : id.toString());
        if (color != Color.NONE) {
            tag.putInt("CustomPotionColor", color);
        }
        data.putString("id", "minecraft:potion");
        data.putInt("Count", 1);
        data.put("tag", tag);
        return ItemStack.of(data);
    }

    public static ItemStack createArmorItemWithColor(CompoundTag data, int color) {
        if (color == Color.NONE) {
            data.getCompound("tag").remove("display");
        } else {
            if (!data.contains("tag", TagHelper.COMPOUND_ID)) {
                data.put("tag", new CompoundTag());
            }
            CompoundTag tag = data.getCompound("tag");
            if (!tag.contains("display", TagHelper.COMPOUND_ID)) {
                tag.put("display", new CompoundTag());
            }
            CompoundTag display = tag.getCompound("display");
            display.putInt("color", color);
        }
        return ItemStack.of(data);
    }
}
