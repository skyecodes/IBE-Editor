package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.node.Group;
import com.github.franckyi.guapi.node.Node;
import com.github.franckyi.guapi.theme.AbstractSkin;

import java.util.Comparator;
import java.util.stream.Collectors;

public abstract class VanillaGroupSkin<N extends Group> extends AbstractSkin<N> {
    @Override
    public boolean preRender(N node) {
        boolean res = false;
        for (Node child : node.getChildren()) {
            res |= child.preRender();
        }
        return res;
    }

    @Override
    public void render(N node, Object matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        for (Node child : node.getChildren().stream()
                .sorted(Comparator.comparingInt(Node::getRenderPriority).reversed()).collect(Collectors.toList())) {
            child.render(matrices, mouseX, mouseY, delta);
        }
    }
}
