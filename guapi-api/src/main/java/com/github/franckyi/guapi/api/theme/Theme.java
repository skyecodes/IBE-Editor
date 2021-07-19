package com.github.franckyi.guapi.api.theme;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.util.NodeType;

public interface Theme {
    <N extends Node> Skin<? super N> provideSkin(N node, NodeType<? extends N> type);
}
