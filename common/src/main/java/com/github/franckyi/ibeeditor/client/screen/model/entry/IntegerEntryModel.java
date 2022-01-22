package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.guapi.api.util.Predicates;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class IntegerEntryModel extends NumberEntryModel<Integer> {
    public IntegerEntryModel(CategoryModel category, MutableComponent label, int value, Consumer<Integer> action) {
        super(category, label, value, action, Predicates.IS_INT, i -> Integer.toString(i), Integer::parseInt);
    }

    public IntegerEntryModel(CategoryModel category, MutableComponent label, int value, Consumer<Integer> action, Predicate<Integer> validator) {
        super(category, label, value, action, validator, Predicates.IS_INT, i -> Integer.toString(i), Integer::parseInt);
    }
}
