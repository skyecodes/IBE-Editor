package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;

public class IntegerEntryModel extends ValueEntryModel<Integer> {
    public IntegerEntryModel(CategoryModel category, MutableComponent label, int value, Consumer<Integer> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }
}
