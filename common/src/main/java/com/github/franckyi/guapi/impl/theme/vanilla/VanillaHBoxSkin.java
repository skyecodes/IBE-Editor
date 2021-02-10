package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.Skin;

public class VanillaHBoxSkin extends VanillaGroupSkin<HBox> {
    public static final Skin<HBox> INSTANCE = new VanillaHBoxSkin();

    private VanillaHBoxSkin() {
    }

    @Override
    public int computeWidth(HBox node) {
        return node.getChildren().parallelStream().mapToInt(Node::getWidth).sum()
                + node.getSpacing() * (node.getChildren().size() - 1);
    }

    @Override
    public int computeHeight(HBox node) {
        return node.getChildren().parallelStream().mapToInt(Node::getHeight).max().orElse(0);
    }
}
