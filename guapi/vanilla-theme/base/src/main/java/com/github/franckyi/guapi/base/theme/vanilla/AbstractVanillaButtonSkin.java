package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.base.theme.DelegatedSkin;

public abstract class AbstractVanillaButtonSkin<N extends Node> extends DelegatedSkin<N> {
    public AbstractVanillaButtonSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeHeight(N node) {
        return 20;
    }
}
