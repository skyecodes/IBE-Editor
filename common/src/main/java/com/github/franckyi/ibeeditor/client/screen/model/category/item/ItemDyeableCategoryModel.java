package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.ArmorColorEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

public class ItemDyeableCategoryModel extends ItemEditorCategoryModel {
    public ItemDyeableCategoryModel(ItemEditorModel editor) {
        super(ModTexts.ARMOR_COLOR, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new ArmorColorEntryModel(this, getColor(), this::setColor));
    }

    private int getColor() {
        CompoundTag display = getTag().getCompound(ItemStack.TAG_DISPLAY);
        return display.contains(ItemStack.TAG_COLOR, Tag.TAG_INT) ? display.getInt(ItemStack.TAG_COLOR) : Color.NONE;
    }

    private void setColor(int value) {
        if (value == Color.NONE && getTag().contains(ItemStack.TAG_DISPLAY, Tag.TAG_COMPOUND)
                && getSubTag(ItemStack.TAG_DISPLAY).contains(ItemStack.TAG_COLOR)) {
            getSubTag(ItemStack.TAG_DISPLAY).remove(ItemStack.TAG_COLOR);
        } else {
            getOrCreateSubTag(ItemStack.TAG_DISPLAY).putInt(ItemStack.TAG_COLOR, value);
        }
    }
}
