package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.gameadapter.api.client.IRenderer;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Toggle;

public interface VanillaToggleSkin<N extends Node & Toggle> {
    default void renderToggle(N node, IMatrices matrices) {
        if (node.isActive()) {
            IRenderer.get().drawRectangle(matrices, node.getX(), node.getY(),
                    node.getX() + node.getWidth(), node.getY() + node.getHeight(), node.getBorderColor());
        }
    }
}
