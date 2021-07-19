package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.IntegerProperty;

public class SimpleIntegerProperty extends AbstractProperty<Integer> implements IntegerProperty {
    public SimpleIntegerProperty() {
        this(0);
    }

    public SimpleIntegerProperty(int value) {
        super(value);
    }
}
