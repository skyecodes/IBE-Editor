package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.guapi.api.Color;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public final class ColoredItemHelper {
    private ColoredItemHelper() {
    }

    public static ItemStack createColoredPotionItem(ResourceLocation potionId, int color) {
        CompoundTag data = new CompoundTag();
        CompoundTag tag = new CompoundTag();
        tag.putString("Potion", potionId == null ? "minecraft:empty" : potionId.toString());
        if (color != Color.NONE) {
            tag.putInt("CustomPotionColor", color);
        }
        data.putString("id", "minecraft:potion");
        data.putInt("Count", 1);
        data.put("tag", tag);
        return ItemStack.of(data);
    }

    public static ItemStack createColoredArmorItem(ItemStack armorItem, int color) {
        CompoundTag data = armorItem.save(new CompoundTag());
        if (color == Color.NONE) {
            data.getCompound("tag").remove("display");
        } else {
            if (!data.contains("tag", Tag.TAG_COMPOUND)) {
                data.put("tag", new CompoundTag());
            }
            CompoundTag tag = data.getCompound("tag");
            if (!tag.contains("display", Tag.TAG_COMPOUND)) {
                tag.put("display", new CompoundTag());
            }
            CompoundTag display = tag.getCompound("display");
            display.putInt("color", color);
        }
        return ItemStack.of(data);
    }
}
