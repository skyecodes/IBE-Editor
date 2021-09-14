package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import java.util.function.Consumer;

public class ArmorColorEntryModel extends IntegerEntryModel {
    public ArmorColorEntryModel(CategoryModel category, int color, Consumer<Integer> action) {
        super(category, ModTexts.ARMOR_COLOR, color, action);
    }

    @Override
    public Type getType() {
        return Type.ARMOR_COLOR;
    }
}
