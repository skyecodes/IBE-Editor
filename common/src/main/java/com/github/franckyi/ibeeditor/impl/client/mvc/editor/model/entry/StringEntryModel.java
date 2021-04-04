package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryType;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;

public class StringEntryModel extends ValueEntryModel<String> {
    public StringEntryModel(CategoryModel category, Text label, String value, Consumer<String> action) {
        super(category, label, value, action);
    }

    @Override
    public EntryType getType() {
        return EntryType.STRING;
    }
}
