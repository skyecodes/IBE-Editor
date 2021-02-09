package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.node.Node;

public abstract class ProvidedSkin<N extends Node> extends AbstractSkin<N> {
    private final N node;

    protected ProvidedSkin(N node) {
        this.node = node;
    }

    @Override
    public void render(N node, Object matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        renderNode(matrices, mouseX, mouseY, delta);
    }

    protected abstract void renderNode(Object matrices, int mouseX, int mouseY, float delta);

    public N getNode() {
        return node;
    }
}
