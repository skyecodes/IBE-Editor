package com.github.franckyi.ibeeditor.client.editor.gui.standard.category.item;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.category.ItemCategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.item.ArmorColorEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.common.TagHelper;
import net.minecraft.nbt.CompoundTag;

public class ItemDyeableCategoryModel extends ItemCategoryModel {
    public ItemDyeableCategoryModel(ItemEditorModel editor) {
        super(ModTexts.ARMOR_COLOR, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new ArmorColorEntryModel(this, getColor(), this::setColor));
    }

    private int getColor() {
        CompoundTag display = getBaseTag().getCompound("display");
        return display.contains("color", TagHelper.INT_ID) ? display.getInt("color") : Color.NONE;
    }

    private void setColor(int value) {
        if (value == Color.NONE) {
            getNewTag().getCompound("display").remove("color");
            if (getNewTag().getCompound("display").isEmpty()) {
                getNewTag().remove("display");
            }
        } else {
            if (getNewTag().contains("display", TagHelper.COMPOUND_ID)) {
                getNewTag().getCompound("display").putInt("color", value);
            } else {
                CompoundTag display = new CompoundTag();
                display.putInt("color", value);
                getNewTag().put("display", display);
            }
        }
    }
}
