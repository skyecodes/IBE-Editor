package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.guapi.event.TypeEvent;

public class TextField extends Labeled {
    private final IntegerProperty maxLengthProperty = PropertyFactory.ofInteger(Integer.MAX_VALUE);

    public TextField() {
        this("");
    }

    public TextField(String text) {
        super(text);
    }

    public int getMaxLength() {
        return maxLengthProperty().getValue();
    }

    public IntegerProperty maxLengthProperty() {
        return maxLengthProperty;
    }

    public void setMaxLength(int value) {
        maxLengthProperty().setValue(value);
    }

    @Override
    public String toString() {
        return "TextField{" +
                "text=" + getText() +
                "}";
    }
}
