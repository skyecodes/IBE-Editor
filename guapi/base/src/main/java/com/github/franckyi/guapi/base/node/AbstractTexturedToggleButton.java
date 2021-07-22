package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.node.TexturedToggleButton;
import com.github.franckyi.guapi.api.util.Color;

public abstract class AbstractTexturedToggleButton extends AbstractTexturedButton implements TexturedToggleButton {
    private final BooleanProperty activeProperty = DataBindings.getPropertyFactory().createBooleanProperty();
    private final IntegerProperty borderColorProperty = DataBindings.getPropertyFactory().createIntegerProperty(Color.rgba(1, 1, 1, 0.8));

    protected AbstractTexturedToggleButton(String textureId, boolean drawButton) {
        super(textureId, drawButton);
    }

    protected AbstractTexturedToggleButton(String textureId, int imageWidth, int imageHeight, boolean drawButton) {
        super(textureId, imageWidth, imageHeight, drawButton);
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
