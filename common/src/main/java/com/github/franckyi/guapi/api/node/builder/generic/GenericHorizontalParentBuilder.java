package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.HorizontalParent;
import com.github.franckyi.guapi.api.node.builder.Builder;

public interface GenericHorizontalParentBuilder<N extends HorizontalParent> extends HorizontalParent, Builder<N> {
    default N fillHeight(boolean value) {
        return with(n -> n.setFillHeight(value));
    }

    default N fillHeight() {
        return fillHeight(true);
    }
}
