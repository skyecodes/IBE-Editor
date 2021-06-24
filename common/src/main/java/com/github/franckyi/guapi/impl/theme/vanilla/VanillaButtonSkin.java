package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.minecraft.Minecraft;

public class VanillaButtonSkin<N extends Button> extends AbstractVanillaButtonSkin<N> {
    public VanillaButtonSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeWidth(N node) {
        return Math.max(90, Minecraft.getClient().getRenderer().getFontWidth(node.getLabel()) + 20);
    }
}
