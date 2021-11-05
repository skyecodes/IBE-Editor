package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.item;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;

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
