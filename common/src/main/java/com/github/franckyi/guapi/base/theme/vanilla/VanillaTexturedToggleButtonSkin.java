package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.TexturedToggleButton;
import com.mojang.blaze3d.vertex.PoseStack;

public class VanillaTexturedToggleButtonSkin<N extends TexturedToggleButton> extends VanillaTexturedButtonSkin<N> implements VanillaToggleSkin<N> {
    public VanillaTexturedToggleButtonSkin(N node) {
        super(node);
    }

    @Override
    public void render(N node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        renderToggle(node, matrices);
    }
}
