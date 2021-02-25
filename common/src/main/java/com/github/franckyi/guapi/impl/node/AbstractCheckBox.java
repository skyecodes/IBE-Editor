package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.gamehooks.api.common.Text;
import com.github.franckyi.guapi.api.node.CheckBox;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public abstract class AbstractCheckBox extends AbstractLabeled implements CheckBox {
    private final BooleanProperty checkedProperty = PropertyFactory.ofBoolean();

    protected AbstractCheckBox() {
        this(emptyText());
    }

    protected AbstractCheckBox(String label) {
        this(text(label));
    }

    protected AbstractCheckBox(Text label) {
        super(label);
        labelProperty().addListener(this::shouldComputeSize);
    }

    @Override
    public BooleanProperty checkedProperty() {
        return checkedProperty;
    }

}
