package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.ToggleButton;
import com.mojang.blaze3d.vertex.PoseStack;

public class VanillaToggleButtonSkin<N extends ToggleButton> extends VanillaButtonSkin<N> implements VanillaToggleSkin<N> {
    public VanillaToggleButtonSkin(N node) {
        super(node);
    }

    @Override
    public void render(N node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        renderToggle(node, matrices);
    }
}
