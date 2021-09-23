package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaWidgetSkinDelegate;

public abstract class AbstractVanillaButtonSkin<N extends Node, W extends VanillaWidgetSkinDelegate> extends AbstractVanillaWidgetSkin<N, W> {
    protected AbstractVanillaButtonSkin(N node, W widget) {
        super(node, widget);
    }

    @Override
    public int computeHeight(N node) {
        return 20;
    }
}
