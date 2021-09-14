package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.gameadapter.api.client.ISprite;
import com.github.franckyi.guapi.api.node.SpriteView;

import java.util.function.Supplier;

public interface GenericSpriteViewBuilder<N extends SpriteView> extends SpriteView, GenericControlBuilder<N> {
    default N spriteFactory(Supplier<ISprite> value) {
        return with(n -> n.setSpriteFactory(value));
    }

    default N imageWidth(int value) {
        return with(n -> n.setImageWidth(value));
    }

    default N imageHeight(int value) {
        return with(n -> n.setImageHeight(value));
    }
}
