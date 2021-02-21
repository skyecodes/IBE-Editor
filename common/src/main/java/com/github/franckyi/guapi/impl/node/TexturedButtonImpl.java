package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.guapi.util.NodeType;

public class TexturedButtonImpl extends AbstractTexturedButton implements TexturedButtonBuilder {
    public TexturedButtonImpl(String textureId, boolean drawButton) {
        super(textureId, drawButton);
    }

    public TexturedButtonImpl(String textureId, int imageWidth, int imageHeight, boolean drawButton) {
        super(textureId, imageWidth, imageHeight, drawButton);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.TEXTURED_BUTTON;
    }

    @Override
    public String toString() {
        return "TexturedButton{\"" + getTextureId() + "\"}";
    }
}
