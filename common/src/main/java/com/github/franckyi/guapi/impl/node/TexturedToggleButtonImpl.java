package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.guapi.api.node.builder.TexturedToggleButtonBuilder;
import com.github.franckyi.guapi.util.NodeType;

public class TexturedToggleButtonImpl extends AbstractTexturedToggleButton implements TexturedToggleButtonBuilder {
    public TexturedToggleButtonImpl(String textureId, boolean drawButton) {
        super(textureId, drawButton);
    }

    public TexturedToggleButtonImpl(String textureId, int imageWidth, int imageHeight, boolean drawButton) {
        super(textureId, imageWidth, imageHeight, drawButton);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.TEXTURED_TOGGLE_BUTTON;
    }
}
