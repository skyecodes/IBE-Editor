package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

import java.util.function.Consumer;

public class IntegerEntryModel extends ValueEntryModel<Integer> {
    public IntegerEntryModel(CategoryModel category, Text label, int value, Consumer<Integer> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }
}
