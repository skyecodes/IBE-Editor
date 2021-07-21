package com.github.franckyi.guapi.api.theme;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.util.NodeType;

public interface Theme {
    <N extends Node> void registerDelegatedSkinRenderer(NodeType<? extends N> type, DelegatedRendererProvider<? super N> delegatedRendererProvider);

    <N extends Node> Skin<? super N> provideSkin(N node, NodeType<? extends N> type);
}
