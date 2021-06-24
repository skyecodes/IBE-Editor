package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.ToggleButton;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.minecraft.api.client.render.Matrices;

public class VanillaToggleButtonSkin<N extends ToggleButton> extends VanillaButtonSkin<N> implements VanillaToggleSkin<N> {
    public VanillaToggleButtonSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public void render(N node, Matrices matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        renderToggle(node, matrices);
    }
}
