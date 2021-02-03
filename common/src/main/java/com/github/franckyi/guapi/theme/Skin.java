package com.github.franckyi.guapi.theme;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.Renderer;
import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.node.Node;

public interface Skin<N extends Node> {
    void render(N node, RenderContext<?> ctx);

    int computeWidth(N node);

    int computeHeight(N node);

    default boolean isHovered(N node, RenderContext<?> ctx) {
        return node.inBounds(ctx.getMouseX(), ctx.getMouseY());
    }

    default <MS> Renderer<MS> renderer() {
        return GameHooks.getRenderer();
    }

    default <MS> Renderer.Font<MS> font() {
        return this.<MS>renderer().font();
    }

    default <MS> Renderer.Shape<MS> shape() {
        return this.<MS>renderer().shape();
    }
}
