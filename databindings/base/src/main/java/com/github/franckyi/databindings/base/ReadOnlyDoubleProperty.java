package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableDoubleValue;
import com.github.franckyi.databindings.api.Property;

public class ReadOnlyDoubleProperty extends AbstractReadOnlyProperty<Double> implements ObservableDoubleValue {
    public ReadOnlyDoubleProperty(Property<Double> property) {
        super(property);
    }
}
