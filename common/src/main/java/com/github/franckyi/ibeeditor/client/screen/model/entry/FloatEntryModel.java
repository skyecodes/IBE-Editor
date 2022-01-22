package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.guapi.api.util.Predicates;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class FloatEntryModel extends NumberEntryModel<Float> {
    public FloatEntryModel(CategoryModel category, MutableComponent label, float value, Consumer<Float> action) {
        super(category, label, value, action, Predicates.IS_FLOAT, i -> Float.toString(i), Float::parseFloat);
    }

    public FloatEntryModel(CategoryModel category, MutableComponent label, float value, Consumer<Float> action, Predicate<Float> validator) {
        super(category, label, value, action, validator, Predicates.IS_FLOAT, i -> Float.toString(i), Float::parseFloat);
    }
}
