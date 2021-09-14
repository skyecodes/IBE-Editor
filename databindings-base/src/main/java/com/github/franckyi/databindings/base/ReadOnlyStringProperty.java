package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableStringValue;
import com.github.franckyi.databindings.api.Property;

public class ReadOnlyStringProperty extends AbstractReadOnlyProperty<String> implements ObservableStringValue {
    public ReadOnlyStringProperty(Property<String> property) {
        super(property);
    }
}
