package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.guapi.theme.Skin;
import com.github.franckyi.guapi.theme.Theme;
import com.github.franckyi.guapi.util.Align;

import java.util.function.Function;

public class Label extends Control {
    private final StringProperty textProperty = PropertyFactory.ofString("");
    private final ObjectProperty<Align.Horizontal> textAlignProperty = PropertyFactory.ofObject(Align.Horizontal.LEFT);
    private final BooleanProperty shadowProperty = PropertyFactory.ofBoolean();
    private final IntegerProperty colorProperty = PropertyFactory.ofInteger(0xffffff);

    public Label() {
        this("");
    }

    public Label(String text) {
        setText(text);
        textProperty.addListener(event -> computeWidth());
        computeSize();
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

    public Align.Horizontal getTextAlign() {
        return textAlignProperty().getValue();
    }

    public ObjectProperty<Align.Horizontal> textAlignProperty() {
        return textAlignProperty;
    }

    public void setTextAlign(Align.Horizontal value) {
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
    @SuppressWarnings("unchecked")
    public Function<Theme, Skin<Label>> getSkinFactory() {
        return Theme::getLabelSkin;
    }

    @Override
    public String toString() {
        return "Label{" +
                "text=" + getText() +
                '}';
    }
}
