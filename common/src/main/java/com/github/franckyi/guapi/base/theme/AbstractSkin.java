package com.github.franckyi.guapi.base.theme;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.RenderHelper;
import com.github.franckyi.guapi.api.event.ScreenEvent;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.api.util.ScreenEventType;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;

import java.util.Random;

public abstract class AbstractSkin<N extends Node> implements Skin<N> {
    private final int debugColor;

    protected AbstractSkin() {
        debugColor = new Random().nextInt(0x1000000) + 0x80000000;
    }

    @Override
    public boolean preRender(N node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        return false;
    }

    @Override
    public void render(N node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(node, guiGraphics);
    }

    @Override
    public void postRender(N node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        switch (Guapi.getDebugMode()) {
            case OFF:
                break;
            case HOVER:
                if (!node.inBounds(mouseX, mouseY)) break;
            case FULL:
                renderDebug(node, guiGraphics);
                break;
        }
        if (!node.getTooltip().isEmpty() && node.isHovered()) {
            RenderHelper.drawTooltip(guiGraphics, node.getTooltip(), mouseX, mouseY);
        }
    }

    @Override
    public <E extends ScreenEvent> void onEvent(ScreenEventType<E> type, E event) {
        type.onEvent(this, event);
    }

    protected void renderDebug(N node, GuiGraphics guiGraphics) {
        RenderHelper.drawRectangle(guiGraphics, node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), debugColor);
    }

    protected void renderBackground(N node, GuiGraphics guiGraphics) {
        RenderHelper.fillRectangle(guiGraphics, node.getLeft(), node.getTop(),
                node.getRight(), node.getBottom(), node.getBackgroundColor());
    }
}
