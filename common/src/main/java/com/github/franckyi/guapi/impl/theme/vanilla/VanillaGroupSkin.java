package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Group;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.impl.theme.AbstractSkin;

public abstract class VanillaGroupSkin<N extends Group> extends AbstractSkin<N> {
    @Override
    public <M> boolean preRender(N node, M matrices, int mouseX, int mouseY, float delta) {
        boolean res = false;
        for (Node child : node.getChildren()) {
            res |= child.preRender(matrices, mouseX, mouseY, delta);
        }
        return res;
    }

    @Override
    public <M> void render(N node, M matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        for (Node child : node.getChildren()) {
            child.render(matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public <M> void postRender(N node, M matrices, int mouseX, int mouseY, float delta) {
        super.postRender(node, matrices, mouseX, mouseY, delta);
        for (Node child : node.getChildren()) {
            child.postRender(matrices, mouseX, mouseY, delta);
        }
    }
}
