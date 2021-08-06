package com.github.franckyi.guapi.base.theme;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.gameadapter.api.client.IRenderer;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.api.util.ScreenEventType;

import java.util.Random;

public abstract class AbstractSkin<N extends Node> implements Skin<N> {
    private final int debugColor;

    protected AbstractSkin() {
        debugColor = new Random().nextInt(0x1000000) + 0x80000000;
    }

    @Override
    public void render(N node, IMatrices matrices, int mouseX, int mouseY, float delta) {
        renderBackground(node, matrices);
    }

    @Override
    public void postRender(N node, IMatrices matrices, int mouseX, int mouseY, float delta) {
        switch (Guapi.getDebugMode()) {
            case OFF:
                break;
            case HOVER:
                if (!node.inBounds(mouseX, mouseY)) break;
            case FULL:
                renderDebug(node, matrices);
                break;
        }
        Skin.super.postRender(node, matrices, mouseX, mouseY, delta);
    }

    @Override
    public <E extends ScreenEvent> void onEvent(ScreenEventType<E> type, E event) {
        type.onEvent(this, event);
    }

    protected void renderDebug(N node, IMatrices matrices) {
        IRenderer.get().drawRectangle(matrices, node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), debugColor);
    }

    protected void renderBackground(N node, IMatrices matrices) {
        IRenderer.get().fillRectangle(matrices, node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), node.getBackgroundColor());
    }
}
