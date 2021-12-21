package com.github.franckyi.ibeeditor.base.common;

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
        return switch (type) {
            case BYTE_ID -> ByteTag.ZERO;
            case SHORT_ID -> ShortTag.valueOf((short) 0);
            case INT_ID -> IntTag.valueOf(0);
            case LONG_ID -> LongTag.valueOf(0);
            case FLOAT_ID -> FloatTag.ZERO;
            case DOUBLE_ID -> DoubleTag.ZERO;
            case BYTE_ARRAY_ID -> new ByteArrayTag(new byte[0]);
            case STRING_ID -> StringTag.valueOf("");
            case LIST_ID -> new ListTag();
            case COMPOUND_ID -> new CompoundTag();
            case INT_ARRAY_ID -> new IntArrayTag(new int[0]);
            case LONG_ARRAY_ID -> new LongArrayTag(new long[0]);
            default -> null;
        };
    }

    public static ItemStack fromPotion(ResourceLocation id, int color) {
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

    public static ItemStack fromArmor(CompoundTag data, int color) {
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
