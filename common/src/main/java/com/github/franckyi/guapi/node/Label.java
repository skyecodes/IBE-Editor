package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.gamehooks.api.common.text.Text;
import com.github.franckyi.guapi.util.Align;
import com.github.franckyi.guapi.util.Color;

public class Label extends Labeled {
    private final ObjectProperty<Align> textAlignProperty = PropertyFactory.ofObject(Align.TOP_LEFT);
    private final BooleanProperty shadowProperty = PropertyFactory.ofBoolean(false);

    public Label() {
        this(Text.EMPTY);
    }

    public Label(String text) {
        this(Text.literal(text));
    }

    public Label(Text text) {
        super(text);
        labelProperty().addListener(this::shouldComputeSize);
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

    @Override
    public String toString() {
        return "Label{" +
                "text=" + getLabel() +
                '}';
    }
}
