package com.github.franckyi.guapi.base.node;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.guapi.api.node.builder.TexturedToggleButtonBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

public class TexturedToggleButtonImpl extends AbstractTexturedToggleButton implements TexturedToggleButtonBuilder {
    public TexturedToggleButtonImpl(IIdentifier textureId, boolean drawButton) {
        super(textureId, drawButton);
    }

    public TexturedToggleButtonImpl(IIdentifier textureId, int imageWidth, int imageHeight, boolean drawButton) {
        super(textureId, imageWidth, imageHeight, drawButton);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.TEXTURED_TOGGLE_BUTTON;
    }
}
