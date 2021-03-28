package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;

public abstract class ValueEntryModel<T> extends LabeledEntryModel {
    protected T defaultValue;
    protected final ObjectProperty<T> valueProperty;
    protected final Consumer<T> action;

    public ValueEntryModel(Text label, T value, Consumer<T> action) {
        super(label);
        defaultValue = value;
        valueProperty = Bindings.getPropertyFactory().ofObject(value);
        this.action = action;
    }

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

    @Override
    public void apply() {
        action.accept(valueProperty.getValue());
        defaultValue = valueProperty.getValue();
    }
}
