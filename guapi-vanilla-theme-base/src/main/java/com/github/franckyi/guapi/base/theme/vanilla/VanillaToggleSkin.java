package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.client.render.Matrices;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Toggle;

public interface VanillaToggleSkin<N extends Node & Toggle> {
    default void renderToggle(N node, Matrices matrices) {
        if (node.isActive()) {
            Game.getClient().getRenderer().drawRectangle(matrices, node.getX(), node.getY(),
                    node.getX() + node.getWidth(), node.getY() + node.getHeight(), node.getBorderColor());
        }
    }
}
