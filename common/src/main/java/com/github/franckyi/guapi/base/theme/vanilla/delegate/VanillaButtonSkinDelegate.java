package com.github.franckyi.guapi.base.theme.vanilla.delegate;

import com.github.franckyi.guapi.api.node.Button;

public class VanillaButtonSkinDelegate<N extends Button> extends net.minecraft.client.gui.components.Button implements VanillaWidgetSkinDelegate {
    protected final N node;

    public VanillaButtonSkinDelegate(N node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel(), button -> {
        });
        this.node = node;
        initLabeledWidget(node);
    }
}
