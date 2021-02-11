package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Group;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.impl.theme.AbstractSkin;

import java.util.Comparator;
import java.util.stream.Collectors;

public abstract class VanillaGroupSkin<N extends Group> extends AbstractSkin<N> {
    @Override
    public boolean preRender(N node, Object matrices, int mouseX, int mouseY, float delta) {
        boolean res = false;
        for (Node child : node.getChildren()) {
            res |= child.preRender(matrices, mouseX, mouseY, delta);
        }
        return res;
    }

    @Override
    public void render(N node, Object matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        for (Node child : node.getChildren()) {
            child.render(matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public void postRender(N node, Object matrices, int mouseX, int mouseY, float delta) {
        for (Node child : node.getChildren()) {
            child.postRender(matrices, mouseX, mouseY, delta);
        }
    }
}
