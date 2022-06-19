package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.node.ToggleButton;
import net.minecraft.network.chat.Component;

public abstract class AbstractToggleButton extends AbstractButton implements ToggleButton {
    private final BooleanProperty activeProperty = BooleanProperty.create();
    private final IntegerProperty borderColorProperty = IntegerProperty.create(Color.fromRGBForRender(1.0, 1.0, 1.0));

    protected AbstractToggleButton() {
    }

    protected AbstractToggleButton(String text) {
        super(text);
    }

    protected AbstractToggleButton(Component label) {
        super(label);
    }

    @Override
    public BooleanProperty activeProperty() {
        return activeProperty;
    }

    @Override
    public IntegerProperty borderColorProperty() {
        return borderColorProperty;
    }

    @Override
    public void action(MouseButtonEvent event) {
        toggle();
    }
}
