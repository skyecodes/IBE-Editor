package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.node.TexturedToggleButton;

public abstract class AbstractTexturedToggleButton extends AbstractTexturedButton implements TexturedToggleButton {
    private final BooleanProperty activeProperty = BooleanProperty.create();
    private final IntegerProperty borderColorProperty = IntegerProperty.create(Color.fromRGBA(1, 1, 1, 0.8));

    protected AbstractTexturedToggleButton(IIdentifier textureId, boolean drawButton) {
        super(textureId, drawButton);
    }

    protected AbstractTexturedToggleButton(IIdentifier textureId, int imageWidth, int imageHeight, boolean drawButton) {
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
