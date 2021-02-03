package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.StringProperty;

public class SimpleStringProperty extends AbstractProperty<String> implements StringProperty {
    public SimpleStringProperty() {
    }

    public SimpleStringProperty(String value) {
        super(value);
    }
}
