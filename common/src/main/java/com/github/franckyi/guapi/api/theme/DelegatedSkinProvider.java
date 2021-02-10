package com.github.franckyi.guapi.api.theme;

import com.github.franckyi.guapi.api.node.Node;

@FunctionalInterface
public interface DelegatedSkinProvider<N extends Node> {
    Skin<N> provide(DelegatedRenderer<?> node);
}
