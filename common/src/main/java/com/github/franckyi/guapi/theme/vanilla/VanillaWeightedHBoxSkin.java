package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.node.Node;
import com.github.franckyi.guapi.node.WeightedHBox;
import com.github.franckyi.guapi.theme.Skin;

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
