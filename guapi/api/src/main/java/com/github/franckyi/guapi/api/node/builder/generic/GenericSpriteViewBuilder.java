package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.SpriteView;

public interface GenericSpriteViewBuilder<N extends SpriteView> extends SpriteView, GenericControlBuilder<N> {
    default N sprite(Object value) {
        return with(n -> n.setSprite(value));
    }

    default N imageWidth(int value) {
        return with(n -> n.setImageWidth(value));
    }

    default N imageHeight(int value) {
        return with(n -> n.setImageHeight(value));
    }
}
