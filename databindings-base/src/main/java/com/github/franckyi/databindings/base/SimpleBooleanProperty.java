package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.BooleanProperty;

public class SimpleBooleanProperty extends AbstractProperty<Boolean> implements BooleanProperty {
    public SimpleBooleanProperty() {
        this(false);
    }

    public SimpleBooleanProperty(boolean value) {
        super(value);
    }
}
