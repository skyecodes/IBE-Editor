package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.RenderHelper;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Toggle;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;

public interface VanillaToggleSkin<N extends Node & Toggle> {
    default void renderToggle(N node, GuiGraphics guiGraphics) {
        if (node.isActive()) {
            RenderHelper.drawRectangle(guiGraphics, node.getX(), node.getY(),
                    node.getX() + node.getWidth(), node.getY() + node.getHeight(), node.getBorderColor());
        }
    }
}
