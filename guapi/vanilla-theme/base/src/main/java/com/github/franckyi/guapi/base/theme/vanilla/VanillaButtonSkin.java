package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;

public class VanillaButtonSkin<N extends Button> extends AbstractVanillaButtonSkin<N> {
    public VanillaButtonSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeWidth(N node) {
        return Math.max(90, Game.getClient().getRenderer().getFontWidth(node.getLabel()) + 20);
    }
}
