package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;

public abstract class Labeled extends Control {
    private final StringProperty textProperty = PropertyFactory.ofString("");

    protected Labeled(String text) {
        setText(text);
    }

    public String getText() {
        return textProperty().getValue();
    }

    public StringProperty textProperty() {
        return textProperty;
    }

    public void setText(String value) {
        textProperty().setValue(value);
    }
}
