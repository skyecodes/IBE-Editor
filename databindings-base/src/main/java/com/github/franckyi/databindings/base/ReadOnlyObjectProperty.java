package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableObjectValue;
import com.github.franckyi.databindings.api.Property;

public class ReadOnlyObjectProperty<T> extends AbstractReadOnlyProperty<T> implements ObservableObjectValue<T> {
    public ReadOnlyObjectProperty(Property<T> property) {
        super(property);
    }
}
