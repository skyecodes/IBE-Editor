package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.WeightedVBox;
import com.github.franckyi.guapi.api.theme.Skin;

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
