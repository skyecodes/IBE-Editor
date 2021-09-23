package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.node.CheckBox;
import net.minecraft.network.chat.Component;

import static com.github.franckyi.guapi.api.GuapiHelper.EMPTY_TEXT;
import static com.github.franckyi.guapi.api.GuapiHelper.text;

public abstract class AbstractCheckBox extends AbstractLabeled implements CheckBox {
    private final BooleanProperty checkedProperty = BooleanProperty.create();

    protected AbstractCheckBox() {
        this(EMPTY_TEXT);
    }

    protected AbstractCheckBox(String label) {
        this(text(label));
    }

    protected AbstractCheckBox(Component label) {
        super(label);
        labelProperty().addListener(this::shouldComputeSize);
    }

    @Override
    public BooleanProperty checkedProperty() {
        return checkedProperty;
    }

}
