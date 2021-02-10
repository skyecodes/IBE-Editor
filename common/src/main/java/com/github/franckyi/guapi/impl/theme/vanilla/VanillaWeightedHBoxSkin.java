package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.WeightedHBox;
import com.github.franckyi.guapi.api.theme.Skin;

public class VanillaWeightedHBoxSkin extends VanillaGroupSkin<WeightedHBox> {
    public static final Skin<WeightedHBox> INSTANCE = new VanillaWeightedHBoxSkin();

    private VanillaWeightedHBoxSkin() {
    }

    @Override
    public int computeWidth(WeightedHBox node) {
        return 200;
    }

    @Override
    public int computeHeight(WeightedHBox node) {
        return node.getChildren().parallelStream().mapToInt(Node::getHeight).max().orElse(0);
    }
}
