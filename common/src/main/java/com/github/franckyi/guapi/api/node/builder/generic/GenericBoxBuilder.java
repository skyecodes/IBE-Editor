package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.Box;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.util.Align;

public interface GenericBoxBuilder<N extends Box> extends Box, GenericGroupBuilder<N> {
    default N spacing(int value) {
        return with(n -> n.setSpacing(value));
    }

    default N align(Align value) {
        return with(n -> n.setAlignment(value));
    }

    default N weight(Node node, int value) {
        return with(n -> n.setWeight(node, value));
    }

    default N weight(int index, int value) {
        return weight(getChildren().get(index), value);
    }
}
