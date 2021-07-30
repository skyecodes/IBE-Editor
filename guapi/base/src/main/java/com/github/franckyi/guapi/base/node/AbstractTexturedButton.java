package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.node.TexturedButton;

public abstract class AbstractTexturedButton extends AbstractImageView implements TexturedButton {
    private final BooleanProperty drawButtonProperty = BooleanProperty.create(true);

    public AbstractTexturedButton(String textureId, boolean drawButton) {
        super(textureId);
        setDrawButton(drawButton);
    }

    public AbstractTexturedButton(String textureId, int imageWidth, int imageHeight, boolean drawButton) {
        super(textureId, imageWidth, imageHeight);
        setDrawButton(drawButton);
    }

    @Override
    public BooleanProperty drawButtonProperty() {
        return drawButtonProperty;
    }
}
