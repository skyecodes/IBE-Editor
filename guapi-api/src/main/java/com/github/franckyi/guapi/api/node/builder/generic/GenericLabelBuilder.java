package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.util.Align;

public interface GenericLabelBuilder<N extends Label> extends Label, GenericLabeledBuilder<N> {
    default N textAlign(Align value) {
        return with(n -> n.setTextAlign(value));
    }

    default N shadow(boolean value) {
        return with(n -> n.setShadow(value));
    }

    default N shadow() {
        return shadow(true);
    }
}
