package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;

public class BooleanEntryModel extends ValueEntryModel<Boolean> {
    public BooleanEntryModel(CategoryModel category, MutableComponent label, boolean value, Consumer<Boolean> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }
}
