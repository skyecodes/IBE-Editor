package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.SpriteView;
import com.github.franckyi.guapi.api.node.builder.SpriteViewBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

public class SpriteViewImpl extends AbstractSpriteView implements SpriteViewBuilder {
    public SpriteViewImpl() {
    }

    public SpriteViewImpl(Object sprite) {
        super(sprite);
    }

    public SpriteViewImpl(Object sprite, int imageWidth, int imageHeight) {
        super(sprite, imageWidth, imageHeight);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<SpriteView> getType() {
        return NodeType.SPRITE_VIEW;
    }
}
