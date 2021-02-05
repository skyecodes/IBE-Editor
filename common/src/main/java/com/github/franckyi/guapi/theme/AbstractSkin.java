package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.node.Node;

import java.util.Random;

public abstract class AbstractSkin<T extends Node> implements Skin<T> {
    private final int debugColor;

    protected AbstractSkin() {
        debugColor = new Random().nextInt(0x1000000) + 0x80000000;
    }

    @Override
    public void render(T node, RenderContext<?> ctx) {
        this.renderBackground(node, ctx);
        if (GUAPI.isDebugMode()) {
            this.renderDebug(node, ctx);
        }
    }

    protected void renderDebug(T node, RenderContext<?> ctx) {
        shape().drawRectangle(ctx.getMatrices(), node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), debugColor);
    }

    protected void renderBackground(T node, RenderContext<?> ctx) {
        shape().fillRectangle(ctx.getMatrices(), node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), node.getBackgroundColor().getValue());
    }
}
