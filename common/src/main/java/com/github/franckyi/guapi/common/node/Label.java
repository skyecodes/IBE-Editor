package com.github.franckyi.guapi.common.node;

import com.github.franckyi.guapi.common.data.*;
import com.github.franckyi.guapi.common.util.Align;
import com.github.franckyi.guapi.common.theme.Skin;
import com.github.franckyi.guapi.common.theme.Theme;

import java.util.function.Function;

public class Label extends Control {
    private final ObjectProperty<String> textProperty = new SimpleObjectProperty<>("");
    private final ObjectProperty<Align.Horizontal> textAlignProperty = new SimpleObjectProperty<>(Align.Horizontal.LEFT);
    private final BooleanProperty shadowProperty = new SimpleBooleanProperty();
    private final IntegerProperty colorProperty = new SimpleIntegerProperty(0xffffff);

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

    public void setText(String value) {
        textProperty().setValue(value);
    }

    public ObjectProperty<String> textProperty() {
        return textProperty;
    }

    public Align.Horizontal getTextAlign() {
        return textAlignProperty().getValue();
    }

    public void setTextAlign(Align.Horizontal value) {
        textAlignProperty().setValue(value);
    }

    public ObjectProperty<Align.Horizontal> textAlignProperty() {
        return textAlignProperty;
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

    public void setColor(int value) {
        colorProperty().setValue(value);
    }

    public IntegerProperty colorProperty() {
        return colorProperty;
    }

    @Override
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
