package com.github.franckyi.guapi.api.theme;

import com.github.franckyi.guapi.api.node.Node;

@FunctionalInterface
public interface DelegatedRendererProvider<N extends Node> {
    DelegatedRenderer<?> provide(N node);
}
