package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.node.Group;
import com.github.franckyi.guapi.node.Node;
import com.github.franckyi.guapi.theme.AbstractSkin;

import java.util.Comparator;
import java.util.stream.Collectors;

public abstract class VanillaGroupSkin<N extends Group> extends AbstractSkin<N> {
    @Override
    public void render(N node, RenderContext<?> ctx) {
        super.render(node, ctx);
        for (Node child : node.getChildren().stream()
                .sorted(Comparator.comparingInt(Node::getRenderPriority).reversed()).collect(Collectors.toList())) {
            child.render(ctx);
        }
    }
}
