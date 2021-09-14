package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableIntegerValue;
import com.github.franckyi.databindings.api.Property;

public class ReadOnlyIntegerProperty extends AbstractReadOnlyProperty<Integer> implements ObservableIntegerValue {
    public ReadOnlyIntegerProperty(Property<Integer> property) {
        super(property);
    }
}
