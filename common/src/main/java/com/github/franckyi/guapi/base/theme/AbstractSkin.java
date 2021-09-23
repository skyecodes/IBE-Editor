package com.github.franckyi.guapi.base.theme;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.RenderHelper;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.api.util.ScreenEventType;
import com.mojang.blaze3d.vertex.PoseStack;

import java.util.Random;

public abstract class AbstractSkin<N extends Node> implements Skin<N> {
    private final int debugColor;

    protected AbstractSkin() {
        debugColor = new Random().nextInt(0x1000000) + 0x80000000;
    }

    @Override
    public boolean preRender(N node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        return false;
    }

    @Override
    public void render(N node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(node, matrices);
    }

    @Override
    public void postRender(N node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        switch (Guapi.getDebugMode()) {
            case OFF:
                break;
            case HOVER:
                if (!node.inBounds(mouseX, mouseY)) break;
            case FULL:
                renderDebug(node, matrices);
                break;
        }
        if (!node.getTooltip().isEmpty() && node.isHovered()) {
            RenderHelper.drawTooltip(matrices, node.getTooltip(), mouseX, mouseY);
        }
    }

    @Override
    public <E extends ScreenEvent> void onEvent(ScreenEventType<E> type, E event) {
        type.onEvent(this, event);
    }

    protected void renderDebug(N node, PoseStack matrices) {
        RenderHelper.drawRectangle(matrices, node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), debugColor);
    }

    protected void renderBackground(N node, PoseStack matrices) {
        RenderHelper.fillRectangle(matrices, node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), node.getBackgroundColor());
    }
}
