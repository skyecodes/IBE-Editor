package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.node.Node;

public interface Theme {
    <N extends Node> Skin<? super N> provideSkin(N node);
}
