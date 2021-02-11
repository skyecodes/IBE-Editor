package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.WeightedVBox;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWeightedVBox extends AbstractVBox implements WeightedVBox {
    protected final Map<Node, Integer> weightMap = new HashMap<>();

    protected AbstractWeightedVBox() {
    }

    protected AbstractWeightedVBox(int spacing) {
        super(spacing);
    }

    protected AbstractWeightedVBox(Node... children) {
        super(children);
    }

    protected AbstractWeightedVBox(Collection<? extends Node> children) {
        super(children);
    }

    protected AbstractWeightedVBox(int spacing, Node... children) {
        this(spacing, Arrays.asList(children));
    }

    protected AbstractWeightedVBox(int spacing, Collection<? extends Node> children) {
        super(spacing, children);
    }

    @Override
    public void setWeight(Node node, int value) {
        if (!getChildren().contains(node)) {
            throw new IllegalArgumentException("This FlexBox doesn't contain the Node " + node);
        }
        Integer old = weightMap.put(node, value);
        if (old != null && old == value) {
            shouldUpdateChildren();
        }
    }

    @Override
    public void resetWeight(Node node) {
        if (weightMap.remove(node) != 1) {
            shouldUpdateChildren();
        }
    }

    @Override
    public int getWeight(Node node) {
        Integer value = weightMap.get(node);
        if (value == null) {
            return 1;
        }
        return value;
    }

    @Override
    protected void updateChildrenSize() {
        super.updateChildrenSize();
        int totalHeight = getHeight() - getPadding().getVertical() - getSpacing() * (getChildren().size() - 1);
        int totalWeight = getChildren().stream().mapToInt(this::getWeight).sum();
        int weightedHeight = totalHeight / totalWeight;
        getChildren().forEach(node -> node.setParentPrefHeight(weightedHeight * getWeight(node)));
    }
}
