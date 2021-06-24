package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.node.ToggleButton;
import com.github.franckyi.minecraft.api.common.text.Text;

public abstract class AbstractToggleButton extends AbstractButton implements ToggleButton {
    private final BooleanProperty activeProperty = DataBindings.getPropertyFactory().createBooleanProperty();

    protected AbstractToggleButton() {
    }

    protected AbstractToggleButton(String text) {
        super(text);
    }

    protected AbstractToggleButton(Text label) {
        super(label);
    }

    @Override
    public BooleanProperty activeProperty() {
        return activeProperty;
    }

    @Override
    public void action(MouseButtonEvent event) {
        toggle();
    }
}
