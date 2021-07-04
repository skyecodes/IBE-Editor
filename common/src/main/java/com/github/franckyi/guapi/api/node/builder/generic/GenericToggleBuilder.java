package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.Toggle;
import com.github.franckyi.guapi.api.node.builder.Builder;

public interface GenericToggleBuilder<N extends Toggle> extends Toggle, Builder<N> {
    default N active(boolean value) {
        return with(n -> n.setActive(value));
    }

    default N active() {
        return active(true);
    }

    default N borderColor(int value) {
        return with(n -> n.setBorderColor(value));
    }
}
