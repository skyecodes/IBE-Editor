package com.github.franckyi.guapi.common.node;

import com.github.franckyi.guapi.common.GUAPI;
import com.github.franckyi.guapi.common.data.ObjectProperty;
import com.github.franckyi.guapi.common.data.Property;
import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.math.Align;
import com.github.franckyi.guapi.common.skin.Skin;
import com.github.franckyi.guapi.common.skin.Theme;

import java.util.function.Function;

public class Label extends Node {
    private final Property<String> textProperty = new ObjectProperty<>("");
    private final Property<Align.Horizontal> textAlignProperty = new ObjectProperty<>(Align.Horizontal.LEFT);

    public Label() {
        this("");
    }

    public Label(String text) {
        setText(text);
    }

    public String getText() {
        return textProperty.getValue();
    }

    public Property<String> textProperty() {
        return textProperty;
    }

    public void setText(String value) {
        textProperty.setValue(value);
    }

    public Align.Horizontal getTextAlign() {
        return textAlignProperty.getValue();
    }

    public Property<Align.Horizontal> textAlignProperty() {
        return textAlignProperty;
    }

    public void setTextAlign(Align.Horizontal value) {
        textAlignProperty.setValue(value);
    }

    @Override
    public Function<Theme, Skin<Label>> getSkinFactory() {
        return Theme::getLabelSkin;
    }
}
