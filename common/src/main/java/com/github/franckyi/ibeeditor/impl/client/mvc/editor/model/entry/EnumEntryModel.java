package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryType;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;

public class EnumEntryModel<E extends Enum<E>> extends ValueEntryModel<E> {
    public EnumEntryModel(CategoryModel category, Text label, E value, Consumer<E> action) {
        super(category, label, value, action);
    }

    @Override
    public EntryType getType() {
        return EntryType.ENUM;
    }
}
