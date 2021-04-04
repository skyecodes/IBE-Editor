package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryType;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;

public class TextEntryModel extends ValueEntryModel<Text> {
    public TextEntryModel(CategoryModel category, Text label, Text value, Consumer<Text> action) {
        super(category, label, value, action);
    }

    @Override
    public EntryType getType() {
        return EntryType.TEXT;
    }
}
