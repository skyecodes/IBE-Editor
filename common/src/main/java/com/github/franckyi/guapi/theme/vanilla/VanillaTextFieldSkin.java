package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.theme.NodeRenderer;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.guapi.theme.LabeledSkin;

public class VanillaTextFieldSkin extends LabeledSkin<TextField> {
    private final NodeRenderer<?> renderer;

    public VanillaTextFieldSkin(NodeRenderer<?> renderer) {
        this.renderer = renderer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <M> NodeRenderer<M> getRenderer() {
        return (NodeRenderer<M>) renderer;
    }

    @Override
    public int computeHeight(TextField node) {
        return 20;
    }

    @Override
    public int computeWidth(TextField node) {
        return 200;
    }
}
