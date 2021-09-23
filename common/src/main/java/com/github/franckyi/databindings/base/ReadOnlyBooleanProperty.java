package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.Property;

public class ReadOnlyBooleanProperty extends AbstractReadOnlyProperty<Boolean> implements ObservableBooleanValue {
    public ReadOnlyBooleanProperty(Property<Boolean> property) {
        super(property);
    }
}
