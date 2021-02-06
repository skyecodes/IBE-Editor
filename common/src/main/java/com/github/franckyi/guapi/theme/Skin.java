package com.github.franckyi.guapi.theme;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.RendererHooks;
import com.github.franckyi.gamehooks.api.client.renderer.FontHooks;
import com.github.franckyi.gamehooks.api.client.renderer.ShapeHooks;
import com.github.franckyi.gamehooks.api.common.TextHooks;
import com.github.franckyi.guapi.EventTarget;
import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.hooks.api.theme.NodeRenderer;
import com.github.franckyi.guapi.node.Node;

public interface Skin<N extends Node> extends EventTarget {
    void render(N node, RenderContext<?> ctx);

    default void tick() {
        if (hasRenderer()) {
            getRenderer().tick();
        }
    }

    int computeWidth(N node);

    int computeHeight(N node);

    default boolean isHovered(N node, RenderContext<?> ctx) {
        return node.inBounds(ctx.getMouseX(), ctx.getMouseY());
    }

    default RendererHooks renderer() {
        return GameHooks.client().renderer();
    }

    default <M, T> FontHooks<M, T> font() {
        return renderer().font();
    }

    default <M> ShapeHooks<M> shape() {
        return renderer().shape();
    }

    default <T> TextHooks<T> text() {
        return GameHooks.common().text();
    }

    default <M> NodeRenderer<M> getRenderer() {
        return null;
    }

    default boolean hasRenderer() {
        return getRenderer() != null;
    }
}
