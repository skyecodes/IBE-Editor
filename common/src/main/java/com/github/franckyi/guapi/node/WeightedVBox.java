package com.github.franckyi.guapi.node;

import java.util.HashMap;
import java.util.Map;

public class WeightedVBox extends VBox {
    protected final Map<Node, Integer> weightMap = new HashMap<>();

    public WeightedVBox() {
        super();
    }

    public WeightedVBox(int spacing) {
        super(spacing);
    }

    public void setWeight(Node node, int value) {
        if (!getChildren().contains(node)) {
            throw new IllegalArgumentException("This FlexBox doesn't contain the Node " + node);
        }
        Integer old = weightMap.put(node, value);
        if (old != null && old == value) {
            shouldUpdateChildrenPos();
        }
    }

    public void resetWeight(Node node) {
        if (weightMap.remove(node) != 1) {
            shouldUpdateChildrenPos();
        }
    }

    public int getWeight(Node node) {
        Integer value = weightMap.get(node);
        if (value == null) {
            return 1;
        }
        return value;
    }

    @Override
    protected void updateChildrenPos() {
        int totalHeight = getHeight() - getPadding().getVertical() - getSpacing() * (getChildren().size() - 1);
        int totalWeight = getChildren().stream().mapToInt(this::getWeight).sum();
        int weightedHeight = totalHeight / totalWeight;
        getChildren().forEach(node -> node.setPrefHeight(weightedHeight * getWeight(node)));
        super.updateChildrenPos();
    }
}
