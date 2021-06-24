package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.node.TexturedToggleButton;

public abstract class AbstractTexturedToggleButton extends AbstractTexturedButton implements TexturedToggleButton {
    private final BooleanProperty activeProperty = DataBindings.getPropertyFactory().createBooleanProperty();

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
    public void action(MouseButtonEvent event) {
        toggle();
    }
}
