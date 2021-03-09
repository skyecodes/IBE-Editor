package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.minecraft.api.common.text.Text;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class AbstractCheckBox extends AbstractLabeled implements CheckBox {
    private final BooleanProperty checkedProperty = Bindings.getPropertyFactory().ofBoolean();

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
