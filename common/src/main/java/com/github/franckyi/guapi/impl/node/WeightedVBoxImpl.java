package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.builder.WeightedVBoxBuilder;
import com.github.franckyi.guapi.util.NodeType;

import java.util.Collection;

public class WeightedVBoxImpl extends AbstractWeightedVBox implements WeightedVBoxBuilder {
    public WeightedVBoxImpl() {
    }

    public WeightedVBoxImpl(int spacing) {
        super(spacing);
    }

    public WeightedVBoxImpl(Node... children) {
        super(children);
    }

    public WeightedVBoxImpl(Collection<? extends Node> children) {
        super(children);
    }

    public WeightedVBoxImpl(int spacing, Node... children) {
        super(spacing, children);
    }

    public WeightedVBoxImpl(int spacing, Collection<? extends Node> children) {
        super(spacing, children);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.WEIGHTED_VBOX;
    }
}
