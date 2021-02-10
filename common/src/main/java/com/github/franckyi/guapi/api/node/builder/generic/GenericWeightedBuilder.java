package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.Group;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Weighted;
import com.github.franckyi.guapi.api.node.builder.Builder;

public interface GenericWeightedBuilder<N extends Weighted> extends Weighted, Group, Builder<N> {
    default N weight(Node node, int value) {
        return with(n -> n.setWeight(node, value));
    }

    default N weight(int index, int value) {
        return weight(getChildren().get(index), value);
    }
}
