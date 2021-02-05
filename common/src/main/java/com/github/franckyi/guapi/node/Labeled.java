package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.guapi.util.Align;
import com.github.franckyi.guapi.util.Color;

public abstract class Labeled extends Control {
    private final StringProperty textProperty = PropertyFactory.ofString("");
    private final ObjectProperty<Align> textAlignProperty = PropertyFactory.ofObject(Align.TOP_LEFT);
    private final BooleanProperty shadowProperty = PropertyFactory.ofBoolean(false);
    private final ObjectProperty<Color.Text> colorProperty = PropertyFactory.ofObject(new Color.Text(255, 255, 255));

    protected Labeled(String text) {
        setText(text);
        textProperty.addListener(event -> computeWidth());
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

    public Color.Text getColor() {
        return colorProperty().getValue();
    }

    public ObjectProperty<Color.Text> colorProperty() {
        return colorProperty;
    }

    public void setColor(Color.Text value) {
        colorProperty().setValue(value);
    }
}
