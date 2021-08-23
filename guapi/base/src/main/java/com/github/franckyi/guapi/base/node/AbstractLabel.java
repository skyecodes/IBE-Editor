package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.util.Align;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class AbstractLabel extends AbstractLabeled implements Label {
    private final ObjectProperty<Align> textAlignProperty = ObjectProperty.create(Align.TOP_LEFT);
    private final BooleanProperty shadowProperty = BooleanProperty.create(false);

    protected AbstractLabel() {
        this(EMPTY_TEXT);
    }

    protected AbstractLabel(String text) {
        this(text, false);
    }

    protected AbstractLabel(IText text) {
        this(text, false);
    }

    protected AbstractLabel(String text, boolean shadow) {
        this(text(text), shadow);
    }

    protected AbstractLabel(IText label, boolean shadow) {
        super(label);
        setShadow(shadow);
        labelProperty().addListener(this::shouldComputeSize);
    }

    @Override
    public ObjectProperty<Align> textAlignProperty() {
        return textAlignProperty;
    }

    @Override
    public BooleanProperty shadowProperty() {
        return shadowProperty;
    }

}
