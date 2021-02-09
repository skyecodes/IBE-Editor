package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.gamehooks.api.common.text.Text;

public class TextField extends Labeled {
    private final StringProperty textProperty = PropertyFactory.ofString("");
    private final IntegerProperty maxLengthProperty = PropertyFactory.ofInteger(Integer.MAX_VALUE);

    public TextField() {
        this("");
    }

    public TextField(String value) {
        this(Text.EMPTY, value);
    }

    public TextField(String label, String value) {
        this(Text.literal(label), value);
    }

    public TextField(Text label, String value) {
        super(label);
        setText(value);
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
                "text=" + getLabel() +
                "}";
    }
}
