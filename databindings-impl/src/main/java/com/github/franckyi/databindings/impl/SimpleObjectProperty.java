package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObjectProperty;

public class SimpleObjectProperty<T> extends AbstractProperty<T> implements ObjectProperty<T> {
    public SimpleObjectProperty() {
    }

    public SimpleObjectProperty(T value) {
        super(value);
    }
}
