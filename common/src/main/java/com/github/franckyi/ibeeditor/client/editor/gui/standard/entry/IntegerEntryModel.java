package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class IntegerEntryModel extends ValueEntryModel<Integer> {
    public IntegerEntryModel(CategoryModel category, MutableComponent label, Integer value, Consumer<Integer> action) {
        super(category, label, value, action);
    }

    public IntegerEntryModel(CategoryModel category, MutableComponent label, Integer value, Consumer<Integer> action, Predicate<Integer> validator) {
        super(category, label, value, action, validator);
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }
}
