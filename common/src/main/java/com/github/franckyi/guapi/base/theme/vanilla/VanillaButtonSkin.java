package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.RenderHelper;
import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaButtonSkinDelegate;

public class VanillaButtonSkin<N extends Button> extends AbstractVanillaButtonSkin<N, VanillaButtonSkinDelegate<N>> {
    public VanillaButtonSkin(N node) {
        this(node, new VanillaButtonSkinDelegate<>(node));
    }

    protected VanillaButtonSkin(N node, VanillaButtonSkinDelegate<N> delegate) {
        super(node, delegate);
    }

    @Override
    public int computeWidth(N node) {
        return Math.max(90, RenderHelper.getFontWidth(node.getLabel()) + 20);
    }
}
