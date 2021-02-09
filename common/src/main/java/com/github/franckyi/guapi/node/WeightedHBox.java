package com.github.franckyi.guapi.node;

import java.util.HashMap;
import java.util.Map;

public class WeightedHBox extends HBox {
    protected final Map<Node, Integer> weightMap = new HashMap<>();

    public WeightedHBox() {
        super();
    }

    public WeightedHBox(int spacing) {
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
        int totalWidth = getWidth() - getPadding().getHorizontal() - getSpacing() * (getChildren().size() - 1);
        int totalWeight = getChildren().stream().mapToInt(this::getWeight).sum();
        int weightedWidth = totalWidth / totalWeight;
        getChildren().forEach(node -> node.setPrefWidth(weightedWidth * getWeight(node)));
        super.updateChildrenPos();
    }
}
