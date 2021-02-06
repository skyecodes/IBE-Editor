package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.node.Node;

@FunctionalInterface
public interface SkinProvider<N extends Node> {
    Skin<N> provide(N node);
}
