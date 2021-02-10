package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.Group;
import com.github.franckyi.guapi.api.node.Node;

import java.util.Collection;

public interface GenericGroupBuilder<N extends Group> extends Group, GenericNodeBuilder<N> {
    default N children(Node... value) {
        return with(n -> n.getChildren().setAll(value));
    }

    default N children(Collection<? extends Node> value) {
        return with(n -> n.getChildren().setAll(value));
    }
}
