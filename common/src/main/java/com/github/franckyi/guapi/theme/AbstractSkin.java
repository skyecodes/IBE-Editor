package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.node.Node;

import java.util.Random;

public abstract class AbstractSkin<N extends Node> implements Skin<N> {
    private final int debugColor;

    protected AbstractSkin() {
        debugColor = new Random().nextInt(0x1000000) + 0x80000000;
    }

    @Override
    public void render(N node, RenderContext<?> ctx) {
        renderBackground(node, ctx);
        if (GUAPI.isDebugMode()) {
            renderDebug(node, ctx);
        }
    }

    protected void renderDebug(N node, RenderContext<?> ctx) {
        shape().drawRectangle(ctx.getMatrices(), node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), debugColor);
    }

    protected void renderBackground(N node, RenderContext<?> ctx) {
        shape().fillRectangle(ctx.getMatrices(), node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), node.getBackgroundColor());
    }
}
