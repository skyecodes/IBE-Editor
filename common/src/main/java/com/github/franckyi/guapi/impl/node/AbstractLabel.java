package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gamehooks.util.common.Text;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.util.Align;

public abstract class AbstractLabel extends AbstractLabeled implements Label {
    private final ObjectProperty<Align> textAlignProperty = PropertyFactory.ofObject(Align.TOP_LEFT);
    private final BooleanProperty shadowProperty = PropertyFactory.ofBoolean(false);

    protected AbstractLabel() {
        this(Text.EMPTY);
    }

    protected AbstractLabel(String text) {
        this(text, false);
    }

    protected AbstractLabel(Text text) {
        this(text, false);
    }

    protected AbstractLabel(String text, boolean shadow) {
        this(Text.literal(text), shadow);
    }

    protected AbstractLabel(Text label, boolean shadow) {
        super(label);
        setShadow(shadow);
        labelProperty().addListener(this::shouldComputeSize);
    }

    @Override
    public Align getTextAlign() {
        return textAlignProperty().getValue();
    }

    @Override
    public ObjectProperty<Align> textAlignProperty() {
        return textAlignProperty;
    }

    @Override
    public void setTextAlign(Align value) {
        textAlignProperty().setValue(value);
    }

    @Override
    public boolean hasShadow() {
        return shadowProperty().getValue();
    }

    @Override
    public BooleanProperty shadowProperty() {
        return shadowProperty;
    }

    @Override
    public void setShadow(boolean value) {
        shadowProperty().setValue(value);
    }
}
