package com.github.franckyi.guapi.common.theme.vanilla;

import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.node.HBox;
import com.github.franckyi.guapi.common.node.Node;
import com.github.franckyi.guapi.common.theme.AbstractSkin;
import com.github.franckyi.guapi.common.theme.Skin;

public class VanillaHBoxSkin extends AbstractSkin<HBox> {
    public static final Skin<HBox> INSTANCE = new VanillaHBoxSkin();

    @Override
    public void render(HBox node, RenderContext<?> ctx) {
        super.render(node, ctx);
        for (Node child : node.getChildren()) {
            child.render(ctx);
        }
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
