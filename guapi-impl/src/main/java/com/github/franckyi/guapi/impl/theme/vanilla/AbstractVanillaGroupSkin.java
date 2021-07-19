package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Group;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.impl.theme.AbstractSkin;
import com.github.franckyi.minecraft.api.client.render.Matrices;

public abstract class AbstractVanillaGroupSkin<N extends Group> extends AbstractSkin<N> {
    @Override
    public boolean preRender(N node, Matrices matrices, int mouseX, int mouseY, float delta) {
        boolean res = false;
        for (Node child : node.getChildren()) {
            res |= child.preRender(matrices, mouseX, mouseY, delta);
        }
        return res;
    }

    @Override
    public void render(N node, Matrices matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        for (Node child : node.getChildren()) {
            child.render(matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public void postRender(N node, Matrices matrices, int mouseX, int mouseY, float delta) {
        super.postRender(node, matrices, mouseX, mouseY, delta);
        for (Node child : node.getChildren()) {
            child.postRender(matrices, mouseX, mouseY, delta);
        }
    }
}
