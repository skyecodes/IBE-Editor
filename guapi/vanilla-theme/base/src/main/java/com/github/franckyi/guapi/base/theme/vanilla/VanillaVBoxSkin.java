package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.VBox;
import com.github.franckyi.guapi.api.theme.Skin;

public final class VanillaVBoxSkin extends AbstractVanillaGroupSkin<VBox> {
    public static final Skin<VBox> INSTANCE = new VanillaVBoxSkin();

    private VanillaVBoxSkin() {
    }

    @Override
    public int computeWidth(VBox node) {
        return node.getChildren().parallelStream().mapToInt(Node::getWidth).max().orElse(0);
    }

    @Override
    public int computeHeight(VBox node) {
        return node.getChildren().parallelStream().mapToInt(Node::getHeight).sum()
                + node.getSpacing() * (node.getChildren().size() - 1);
    }
}
