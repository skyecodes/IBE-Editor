package com.github.franckyi.guapi.theme;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.node.Node;

public interface Skin<N extends Node> {
    void render(N node, RenderContext<?> ctx);

    int computeWidth(N node);

    int computeHeight(N node);

    default boolean isHovered(N node, RenderContext<?> ctx) {
        return node.inBounds(ctx.getMouseX(), ctx.getMouseY());
    }

    default <M> Renderer<M> renderer() {
        return GameHooks.client().renderer();
    }

    default <M> Renderer.Font<M> font() {
        return this.<M>renderer().font();
    }

    default <M> Renderer.Shape<M> shape() {
        return this.<M>renderer().shape();
    }

    default <M> Renderer.Widget<M> widget() {
        return this.<M>renderer().widget();
    }

    default Renderer.System system() {
        return renderer().system();
    }
}
