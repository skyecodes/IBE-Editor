package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;

public class StringEntryModel extends ValueEntryModel<String> {
    public StringEntryModel(CategoryModel category, MutableComponent label, String value, Consumer<String> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.STRING;
    }
}
