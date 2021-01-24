package com.github.franckyi.guapi.common.skin;

import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.node.Node;

public interface Skin<N extends Node> {
    void render(N node, RenderContext<?> ctx);
    int computeWidth(N node);
    int computeHeight(N node);
}
