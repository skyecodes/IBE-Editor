package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.SpacedGroup;
import com.github.franckyi.guapi.api.node.builder.Builder;
import com.github.franckyi.guapi.util.Align;

public interface GenericSpacedGroupBuilder<N extends SpacedGroup> extends SpacedGroup, GenericGroupBuilder<N> {
    default N spacing(int value) {
        return with(n -> n.setSpacing(value));
    }

    default N align(Align value) {
        return with(n -> n.setAlignment(value));
    }
}
