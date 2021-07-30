package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ArmorColorEntryModel extends IntegerEntryModel {
    public ArmorColorEntryModel(CategoryModel category, int color, Consumer<Integer> action) {
        super(category, translated("ibeeditor.gui.armor_color"), color, action);
    }

    @Override
    public Type getType() {
        return Type.ARMOR_COLOR;
    }
}
