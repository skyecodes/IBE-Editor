package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.BooleanProperty;

public class SimpleBooleanProperty extends AbstractProperty<Boolean> implements BooleanProperty {
    public SimpleBooleanProperty(boolean value) {
        super(value);
    }
}
