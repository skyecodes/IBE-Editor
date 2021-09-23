package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.RenderHelper;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Toggle;
import com.mojang.blaze3d.vertex.PoseStack;

public interface VanillaToggleSkin<N extends Node & Toggle> {
    default void renderToggle(N node, PoseStack matrices) {
        if (node.isActive()) {
            RenderHelper.drawRectangle(matrices, node.getX(), node.getY(),
                    node.getX() + node.getWidth(), node.getY() + node.getHeight(), node.getBorderColor());
        }
    }
}
