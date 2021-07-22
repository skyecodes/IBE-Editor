package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.CheckBox;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class AbstractCheckBox extends AbstractLabeled implements CheckBox {
    private final BooleanProperty checkedProperty = DataBindings.getPropertyFactory().createBooleanProperty();

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
