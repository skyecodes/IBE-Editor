package com.github.franckyi.gamehooks.util.common.tag;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.ObjectProperty;

public abstract class AbstractTag<T> implements Tag<T> {
    private final ObjectProperty<T> valueProperty = PropertyFactory.ofObject();

    public AbstractTag() {
    }

    public AbstractTag(T value) {
        setValue(value);
    }

    @Override
    public ObjectProperty<T> valueProperty() {
        return valueProperty;
    }
}
