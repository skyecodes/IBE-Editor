package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.event.ScreenEvent;
import com.github.franckyi.guapi.event.ScreenEventType;
import com.github.franckyi.guapi.node.Node;

import java.util.Random;

public abstract class AbstractSkin<N extends Node> implements Skin<N> {
    private final int debugColor;

    protected AbstractSkin() {
        debugColor = new Random().nextInt(0x1000000) + 0x80000000;
    }

    @Override
    public void render(N node, Object matrices, int mouseX, int mouseY, float delta) {
        renderBackground(node, matrices);
        if (GUAPI.isDebugMode()) {
            renderDebug(node, matrices);
        }
    }

    @Override
    public <E extends ScreenEvent> void onEvent(ScreenEventType<E> type, E event) {
        type.onEvent(this, event);
    }

    protected void renderDebug(N node, Object matrices) {
        shape().drawRectangle(matrices, node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), debugColor);
    }

    protected void renderBackground(N node, Object matrices) {
        shape().fillRectangle(matrices, node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), node.getBackgroundColor());
    }
}
