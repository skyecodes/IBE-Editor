package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Toggle;
import com.github.franckyi.guapi.util.Color;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.client.render.Matrices;

public interface VanillaToggleSkin<N extends Node & Toggle> {
    default void renderToggle(N node, Matrices matrices) {
        if (node.isActive()) {
            Minecraft.getClient().getRenderer().drawRectangle(matrices, node.getX(), node.getY(),
                    node.getX() + node.getWidth(), node.getY() + node.getHeight(), Color.rgb(1.0, 1.0, 1.0));
        }
    }
}
