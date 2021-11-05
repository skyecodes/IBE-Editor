package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class ValueEntryModel<T> extends LabeledEntryModel {
    protected T defaultValue;
    private final ObjectProperty<T> valueProperty;
    private final BooleanProperty valueChangedProperty;
    protected Predicate<T> validator;
    protected final Consumer<T> action;

    protected ValueEntryModel(CategoryModel category, MutableComponent label, T value, Consumer<T> action) {
        this(category, label, value, action, t -> true);
    }

    protected ValueEntryModel(CategoryModel category, MutableComponent label, T value, Consumer<T> action, Predicate<T> validator) {
        super(category, label);
        defaultValue = value;
        valueProperty = ObjectProperty.create(value);
        valueChangedProperty = BooleanProperty.create();
        this.validator = validator;
        this.action = action;
        valueProperty.addListener(newVal -> valueChangedProperty.setValue(!Objects.equals(defaultValue, newVal)));
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

    public boolean hasValueChanged() {
        return valueChangedProperty().getValue();
    }

    public BooleanProperty valueChangedProperty() {
        return valueChangedProperty;
    }

    public void setValueChanged(boolean value) {
        valueChangedProperty().setValue(value);
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
