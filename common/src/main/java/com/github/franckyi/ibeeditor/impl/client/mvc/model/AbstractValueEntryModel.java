package com.github.franckyi.ibeeditor.impl.client.mvc.model;

import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.gamehooks.util.common.text.Text;
import com.github.franckyi.ibeeditor.api.client.mvc.model.ValueEntryModel;

import java.util.function.Consumer;

public abstract class AbstractValueEntryModel<T> extends AbstractEntryModel implements ValueEntryModel<T> {
    private final T defaultValue;
    private final Property<T> valueProperty;
    protected final Consumer<T> apply;

    public AbstractValueEntryModel(Text label, T defaultValue, Property<T> valueProperty, Consumer<T> apply) {
        super(label);
        this.defaultValue = defaultValue;
        this.valueProperty = valueProperty;
        this.apply = apply;
    }

    @Override
    public T getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Property<T> valueProperty() {
        return valueProperty;
    }

    @Override
    public void apply() {
        apply.accept(getValue());
    }
}
