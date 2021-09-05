package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class ValueEntryModel<T> extends LabeledEntryModel {
    protected T defaultValue;
    private final ObjectProperty<T> valueProperty;
    protected Predicate<T> validator;
    protected final Consumer<T> action;

    protected ValueEntryModel(CategoryModel category, MutableComponent label, T value, Consumer<T> action) {
        this(category, label, value, action, t -> true);
    }

    protected ValueEntryModel(CategoryModel category, IText label, T value, Consumer<T> action, Predicate<T> validator) {
        super(category, label);
        defaultValue = value;
        valueProperty = ObjectProperty.create(value);
        this.validator = validator;
        this.action = action;
    }

    @Override
    public void reset() {
        valueProperty.setValue(defaultValue);
    }

    public T getValue() {
        return valueProperty().getValue();
    }

    public ObjectProperty<T> valueProperty() {
        return valueProperty;
    }

    public void setValue(T value) {
        valueProperty().setValue(value);
    }

    public boolean validate(T value) {
        return validator.test(value);
    }

    @Override
    public void apply() {
        action.accept(getValue());
        defaultValue = getValue();
    }
}
