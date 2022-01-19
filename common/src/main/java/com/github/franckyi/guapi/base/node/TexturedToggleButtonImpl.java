package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.TexturedToggleButton;
import com.github.franckyi.guapi.api.node.builder.TexturedToggleButtonBuilder;
import net.minecraft.resources.ResourceLocation;

public class TexturedToggleButtonImpl extends AbstractTexturedToggleButton implements TexturedToggleButtonBuilder {
    public TexturedToggleButtonImpl(ResourceLocation textureId, boolean drawButton) {
        super(textureId, drawButton);
    }

    public TexturedToggleButtonImpl(ResourceLocation textureId, int imageWidth, int imageHeight, boolean drawButton) {
        super(textureId, imageWidth, imageHeight, drawButton);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<?> getType() {
        return TexturedToggleButton.class;
    }
}
