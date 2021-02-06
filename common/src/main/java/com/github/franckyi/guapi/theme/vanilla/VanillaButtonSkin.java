package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.theme.NodeRenderer;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.theme.LabeledSkin;

public class VanillaButtonSkin extends LabeledSkin<Button> {
    private final NodeRenderer<?> renderer;

    public VanillaButtonSkin(NodeRenderer<?> renderer) {
        this.renderer = renderer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <M> NodeRenderer<M> getRenderer() {
        return (NodeRenderer<M>) renderer;
    }

    @Override
    public int computeWidth(Button node) {
        return Math.max(90, super.computeWidth(node) + 20);
    }

    @Override
    public int computeHeight(Button node) {
        return 20;
    }
}
