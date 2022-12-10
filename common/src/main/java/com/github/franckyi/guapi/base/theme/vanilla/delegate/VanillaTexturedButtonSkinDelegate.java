package com.github.franckyi.guapi.base.theme.vanilla.delegate;

import com.github.franckyi.guapi.api.node.TexturedButton;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class VanillaTexturedButtonSkinDelegate<N extends TexturedButton> extends Button implements VanillaWidgetSkinDelegate {
    protected final N node;

    public VanillaTexturedButtonSkinDelegate(N node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getTooltip().isEmpty() ? Component.empty() : node.getTooltip().get(0), button -> {
        }, Supplier::get);
        this.node = node;
        initNodeWidget(node);
    }

    @Override
    public void renderButton(@NotNull PoseStack matrices, int mouseX, int mouseY, float delta) {
        if (node.isDrawButton()) {
            super.renderButton(matrices, mouseX, mouseY, delta);
        }
    }
}
