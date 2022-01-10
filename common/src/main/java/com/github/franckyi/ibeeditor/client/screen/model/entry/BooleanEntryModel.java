package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
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
