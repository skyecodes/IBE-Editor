package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.WeightedHBox;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWeightedHBox extends AbstractHBox implements WeightedHBox {
    protected final Map<Node, Integer> weightMap = new HashMap<>();

    protected AbstractWeightedHBox() {
    }

    protected AbstractWeightedHBox(int spacing) {
        super(spacing);
    }

    protected AbstractWeightedHBox(Node... children) {
        super(children);
    }

    protected AbstractWeightedHBox(Collection<? extends Node> children) {
        super(children);
    }

    protected AbstractWeightedHBox(int spacing, Node... children) {
        this(spacing, Arrays.asList(children));
    }

    protected AbstractWeightedHBox(int spacing, Collection<? extends Node> children) {
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
        int totalWidth = getWidth() - getPadding().getHorizontal() - getSpacing() * (getChildren().size() - 1);
        int totalWeight = getChildren().stream().mapToInt(this::getWeight).sum();
        int weightedWidth = totalWidth / totalWeight;
        getChildren().forEach(node -> node.setParentPrefWidth(weightedWidth * getWeight(node)));
    }
}
