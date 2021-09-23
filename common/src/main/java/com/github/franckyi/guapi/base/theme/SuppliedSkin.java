package com.github.franckyi.guapi.base.theme;

import com.github.franckyi.guapi.api.node.Node;

public abstract class SuppliedSkin<N extends Node> extends AbstractSkin<N> {
    protected final N node;

    protected SuppliedSkin(N node) {
        this.node = node;
    }
}
