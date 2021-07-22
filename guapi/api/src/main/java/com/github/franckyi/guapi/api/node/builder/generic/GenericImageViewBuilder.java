package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.ImageView;

public interface GenericImageViewBuilder<N extends ImageView> extends ImageView, GenericControlBuilder<N> {
    default N textureId(String value) {
        return with(n -> n.setTextureId(value));
    }

    default N imageX(int value) {
        return with(n -> n.setImageX(value));
    }

    default N imageY(int value) {
        return with(n -> n.setImageY(value));
    }

    default N imageWidth(int value) {
        return with(n -> n.setImageWidth(value));
    }

    default N imageHeight(int value) {
        return with(n -> n.setImageHeight(value));
    }
}