package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.Spaced;
import com.github.franckyi.guapi.api.node.builder.Builder;

public interface GenericSpacedBuilder<N extends Spaced> extends Spaced, Builder<N> {
    default N spacing(int value) {
        return with(n -> n.setSpacing(value));
    }
}
