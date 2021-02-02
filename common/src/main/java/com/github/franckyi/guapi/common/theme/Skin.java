package com.github.franckyi.guapi.common.theme;

import com.github.franckyi.guapi.common.GUAPI;
import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.hooks.Renderer;
import com.github.franckyi.guapi.common.node.Node;

import java.util.Random;

public interface Skin<N extends Node> {
    void render(N node, RenderContext<?> ctx);

    int computeWidth(N node);

    int computeHeight(N node);

    default boolean isHovered(N node, RenderContext<?> ctx) {
        return node.inBounds(ctx.getMouseX(), ctx.getMouseY());
    }

    @SuppressWarnings("unchecked")
    default <T> Renderer<T> renderer() {
        return (Renderer<T>) GUAPI.getRenderer();
    }

    default <T> Renderer.Font<T> font() {
        return this.<T>renderer().font();
    }

    default <T> Renderer.Shape<T> shape() {
        return this.<T>renderer().shape();
    }
}
