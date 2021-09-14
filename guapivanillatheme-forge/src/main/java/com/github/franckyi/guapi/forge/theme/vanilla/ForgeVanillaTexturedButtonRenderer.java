package com.github.franckyi.guapi.forge.theme.vanilla;

import com.github.franckyi.guapi.api.node.TexturedButton;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

import javax.annotation.Nonnull;

public class ForgeVanillaTexturedButtonRenderer extends Button implements ForgeVanillaDelegateRenderer {
    private final TexturedButton node;

    public ForgeVanillaTexturedButtonRenderer(TexturedButton node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getTooltip().isEmpty() ? TextComponent.EMPTY : (Component) node.getTooltip().get(0), button -> {
        });
        this.node = node;
        initNode(node, this);
    }

    @Override
    public void renderButton(@Nonnull PoseStack matrices, int mouseX, int mouseY, float delta) {
        if (node.isDrawButton()) {
            super.renderButton(matrices, mouseX, mouseY, delta);
        }
    }
}
