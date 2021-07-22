package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.TexturedButton;

public interface GenericTexturedButtonBuilder<N extends TexturedButton> extends TexturedButton, GenericImageViewBuilder<N> {
    default N drawButton(boolean value) {
        return with(n -> n.setDrawButton(value));
    }
}
