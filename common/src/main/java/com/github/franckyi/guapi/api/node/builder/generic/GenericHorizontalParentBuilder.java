package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.HorizontalParent;
import com.github.franckyi.guapi.api.node.builder.Builder;
import com.github.franckyi.guapi.util.Align;

public interface GenericHorizontalParentBuilder<N extends HorizontalParent> extends HorizontalParent, Builder<N> {
    default N fillHeight(boolean value) {
        return with(n -> n.setFillHeight(value));
    }
}
