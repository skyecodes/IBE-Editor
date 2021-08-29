package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ItemCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ArmorColorEntryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

public class ItemDyeableCategoryModel extends ItemCategoryModel {
    public ItemDyeableCategoryModel(ItemEditorModel editor) {
        super(ModTexts.ARMOR_COLOR, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new ArmorColorEntryModel(this, getColor(), this::setColor));
    }

    private int getColor() {
        ICompoundTag display = getBaseTag().getCompound("display");
        return display.contains("color", ITag.INT_ID) ? display.getInt("color") : Color.NONE;
    }

    private void setColor(int value) {
        if (value == Color.NONE) {
            getNewTag().getCompound("display").remove("color");
            if (getNewTag().getCompound("display").isEmpty()) {
                getNewTag().remove("display");
            }
        } else {
            if (getNewTag().contains("display", ITag.COMPOUND_ID)) {
                getNewTag().getCompound("display").putInt("color", value);
            } else {
                ICompoundTag display = ICompoundTag.create();
                display.putInt("color", value);
                getNewTag().put("display", display);
            }
        }
    }
}
