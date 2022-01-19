package com.github.franckyi.guapi.api.theme;

import com.github.franckyi.guapi.api.node.Node;

public interface Theme {
    <N extends Node> Skin<? super N> supplySkin(N node, Class<? extends N> type);
}
