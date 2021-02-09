package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.node.Node;
import com.github.franckyi.guapi.node.VBox;
import com.github.franckyi.guapi.node.WeightedVBox;
import com.github.franckyi.guapi.theme.Skin;

public class VanillaWeightedVBoxSkin extends VanillaGroupSkin<WeightedVBox> {
    public static final Skin<WeightedVBox> INSTANCE = new VanillaWeightedVBoxSkin();

    private VanillaWeightedVBoxSkin() {
    }

    @Override
    public int computeWidth(WeightedVBox node) {
        return node.getChildren().parallelStream().mapToInt(Node::getWidth).max().orElse(0);
    }

    @Override
    public int computeHeight(WeightedVBox node) {
        return 200;
    }
}
