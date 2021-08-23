package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

import java.util.function.Consumer;

public class EnumEntryModel<E extends Enum<E>> extends ValueEntryModel<E> {
    public EnumEntryModel(CategoryModel category, IText label, E value, Consumer<E> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.ENUM;
    }
}
