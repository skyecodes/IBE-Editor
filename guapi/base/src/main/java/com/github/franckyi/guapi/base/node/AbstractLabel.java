package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.util.Align;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class AbstractLabel extends AbstractLabeled implements Label {
    private final ObjectProperty<Align> textAlignProperty = DataBindings.getPropertyFactory().createObjectProperty(Align.TOP_LEFT);
    private final BooleanProperty shadowProperty = DataBindings.getPropertyFactory().createBooleanProperty(false);

    protected AbstractLabel() {
        this(emptyText());
    }

    protected AbstractLabel(String text) {
        this(text, false);
    }

    protected AbstractLabel(Text text) {
        this(text, false);
    }

    protected AbstractLabel(String text, boolean shadow) {
        this(text(text), shadow);
    }

    protected AbstractLabel(Text label, boolean shadow) {
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
