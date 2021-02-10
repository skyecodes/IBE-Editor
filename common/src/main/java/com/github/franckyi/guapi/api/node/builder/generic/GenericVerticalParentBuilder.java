package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.VerticalParent;
import com.github.franckyi.guapi.api.node.builder.Builder;
import com.github.franckyi.guapi.util.Align;

public interface GenericVerticalParentBuilder<N extends VerticalParent> extends VerticalParent, Builder<N> {
    default N align(Align.Horizontal value) {
        return with(n -> n.setAlignment(value));
    }
}
