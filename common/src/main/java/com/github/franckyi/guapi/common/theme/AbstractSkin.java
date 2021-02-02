package com.github.franckyi.guapi.common.theme;

import com.github.franckyi.guapi.common.GUAPI;
import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.node.Node;

import java.util.Random;

public abstract class AbstractSkin<T extends Node> implements Skin<T> {
    private final int debugColor;

    protected AbstractSkin() {
        debugColor = new Random().nextInt(0x1000000) + 0xff000000;
    }

    @Override
    public void render(T node, RenderContext<?> ctx) {
        if (GUAPI.isDebugMode()) {
            shape().drawRectangle(ctx.getMatrixStack(), node.getX(), node.getY(),
                    node.getX() + node.getWidth(), node.getY() + node.getHeight(), debugColor);
        }
    }
}
