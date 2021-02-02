package com.github.franckyi.guapi.common.theme.vanilla;

import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.node.Node;
import com.github.franckyi.guapi.common.node.VBox;
import com.github.franckyi.guapi.common.theme.AbstractSkin;
import com.github.franckyi.guapi.common.theme.Skin;

public class VanillaVBoxSkin extends AbstractSkin<VBox> {
    public static final Skin<VBox> INSTANCE = new VanillaVBoxSkin();

    @Override
    public void render(VBox node, RenderContext<?> ctx) {
        super.render(node, ctx);
        for (Node child : node.getChildren()) {
            child.render(ctx);
        }
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
