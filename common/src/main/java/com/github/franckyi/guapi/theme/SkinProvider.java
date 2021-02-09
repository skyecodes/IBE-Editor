package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.node.Node;

@FunctionalInterface
public interface SkinProvider<N extends Node> {
    Skin<? super N> provide(N node);
}
