package com.github.franckyi.guapi.api.theme;

import com.github.franckyi.guapi.api.node.Node;

@FunctionalInterface
public interface SkinProvider<N extends Node> {
    Skin<? super N> provide(N node);
}
