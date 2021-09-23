package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.DoubleProperty;

public class SimpleDoubleProperty extends AbstractProperty<Double> implements DoubleProperty {
    public SimpleDoubleProperty() {
        this(0);
    }

    public SimpleDoubleProperty(double value) {
        super(value);
    }
}
