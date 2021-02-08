package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.guapi.util.Align;
import com.github.franckyi.guapi.util.Color;

public class Label extends Labeled {
    private final ObjectProperty<Align> textAlignProperty = PropertyFactory.ofObject(Align.TOP_LEFT);
    private final BooleanProperty shadowProperty = PropertyFactory.ofBoolean(false);
    private final IntegerProperty colorProperty = PropertyFactory.ofInteger(Color.rgb(255, 255, 255));

    public Label() {
        this("");
    }

    public Label(String text) {
        super(text);
        textProperty().addListener(this::shouldComputeSize);
    }

    public Align getTextAlign() {
        return textAlignProperty().getValue();
    }

    public ObjectProperty<Align> textAlignProperty() {
        return textAlignProperty;
    }

    public void setTextAlign(Align value) {
        textAlignProperty().setValue(value);
    }

    public boolean hasShadow() {
        return shadowProperty().getValue();
    }

    public BooleanProperty shadowProperty() {
        return shadowProperty;
    }

    public void setShadow(boolean value) {
        shadowProperty().setValue(value);
    }

    public int getColor() {
        return colorProperty().getValue();
    }

    public IntegerProperty colorProperty() {
        return colorProperty;
    }

    public void setColor(int value) {
        colorProperty().setValue(value);
    }

    @Override
    public String toString() {
        return "Label{" +
                "text=" + getText() +
                '}';
    }
}
