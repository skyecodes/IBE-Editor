package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ItemCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ArmorColorEntryModel;

public class ItemDyeableCategoryModel extends ItemCategoryModel {
    public ItemDyeableCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.armor_color", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new ArmorColorEntryModel(this, getColor(), this::setColor));
    }

    private int getColor() {
        CompoundTag display = getBaseTag().getCompound("display");
        return display.contains("color", Tag.INT_ID) ? display.getInt("color") : Color.NONE;
    }

    private void setColor(int value) {
        if (value == Color.NONE) {
            getNewTag().getCompound("display").remove("color");
            if (getNewTag().getCompound("display").isEmpty()) {
                getNewTag().remove("display");
            }
        } else {
            if (getNewTag().contains("display", Tag.COMPOUND_ID)) {
                getNewTag().getCompound("display").putInt("color", value);
            } else {
                CompoundTag display = CompoundTag.create();
                display.putInt("color", value);
                getNewTag().put("display", display);
            }
        }
    }
}
