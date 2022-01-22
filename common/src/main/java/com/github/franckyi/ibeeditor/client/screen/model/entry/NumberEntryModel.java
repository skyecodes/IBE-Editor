package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class NumberEntryModel<T extends Number> extends ValueEntryModel<T> {
    private final Predicate<String> textPredicate;
    private final Function<T, String> toStringFunction;
    private final Function<String, T> toNumberFunction;

    protected NumberEntryModel(CategoryModel category, MutableComponent label, T value, Consumer<T> action, Predicate<String> textPredicate, Function<T, String> toStringFunction, Function<String, T> toNumberFunction) {
        super(category, label, value, action);
        this.textPredicate = textPredicate;
        this.toStringFunction = toStringFunction;
        this.toNumberFunction = toNumberFunction;
    }

    protected NumberEntryModel(CategoryModel category, MutableComponent label, T value, Consumer<T> action, Predicate<T> validator, Predicate<String> textPredicate, Function<T, String> toStringFunction, Function<String, T> toNumberFunction) {
        super(category, label, value, action, validator);
        this.textPredicate = textPredicate;
        this.toStringFunction = toStringFunction;
        this.toNumberFunction = toNumberFunction;
    }

    public Predicate<String> getTextPredicate() {
        return textPredicate;
    }

    public Function<T, String> getToStringFunction() {
        return toStringFunction;
    }

    public Function<String, T> getToNumberFunction() {
        return toNumberFunction;
    }

    @Override
    public Type getType() {
        return Type.NUMBER;
    }
}
