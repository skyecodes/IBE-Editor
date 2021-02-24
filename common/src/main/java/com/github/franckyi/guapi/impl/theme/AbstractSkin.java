package com.github.franckyi.guapi.impl.theme;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.util.ScreenEventType;

import java.util.Random;

public abstract class AbstractSkin<N extends Node> implements Skin<N> {
    private final int debugColor;

    protected AbstractSkin() {
        debugColor = new Random().nextInt(0x1000000) + 0x80000000;
    }

    @Override
    public <M> void render(N node, M matrices, int mouseX, int mouseY, float delta) {
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
        GameHooks.client().getRenderer().drawRectangle(matrices, node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), debugColor);
    }

    protected void renderBackground(N node, Object matrices) {
        GameHooks.client().getRenderer().fillRectangle(matrices, node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), node.getBackgroundColor());
    }
}
