package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.node.TexturedButton;

public abstract class AbstractTexturedButton extends AbstractImageView implements TexturedButton {
    private final BooleanProperty drawButtonProperty = Bindings.getPropertyFactory().ofBoolean(true);

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
