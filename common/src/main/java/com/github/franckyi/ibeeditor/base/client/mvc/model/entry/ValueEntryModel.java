package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class ValueEntryModel<T> extends LabeledEntryModel {
    protected T defaultValue;
    private final ObjectProperty<T> valueProperty;
    private final ObservableBooleanValue valueChangedProperty;
    protected final Consumer<T> action;

    public ValueEntryModel(CategoryModel category, MutableComponent label, T value, Consumer<T> action) {
        super(category, label);
        defaultValue = value;
        valueProperty = ObjectProperty.create(value);
        valueChangedProperty = valueProperty.mapToBoolean(v -> !Objects.equals(v, defaultValue));
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

    public ObservableBooleanValue valueChangedProperty() {
        return valueChangedProperty;
    }

    @Override
    public void apply() {
        action.accept(getValue());
        defaultValue = getValue();
    }
}
