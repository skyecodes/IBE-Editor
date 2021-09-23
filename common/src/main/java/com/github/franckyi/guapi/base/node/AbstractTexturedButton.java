package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.node.TexturedButton;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractTexturedButton extends AbstractImageView implements TexturedButton {
    private final BooleanProperty drawButtonProperty = BooleanProperty.create(true);

    public AbstractTexturedButton(ResourceLocation textureId, boolean drawButton) {
        super(textureId);
        setDrawButton(drawButton);
    }

    public AbstractTexturedButton(ResourceLocation textureId, int imageWidth, int imageHeight, boolean drawButton) {
        super(textureId, imageWidth, imageHeight);
        setDrawButton(drawButton);
    }

    @Override
    public BooleanProperty drawButtonProperty() {
        return drawButtonProperty;
    }
}
