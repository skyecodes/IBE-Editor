package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.TexturedToggleButton;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.minecraft.api.client.render.Matrices;

public class VanillaTexturedToggleButtonSkin<N extends TexturedToggleButton> extends VanillaTexturedButtonSkin<N> implements VanillaToggleSkin<N> {
    public VanillaTexturedToggleButtonSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public void render(N node, Matrices matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        renderToggle(node, matrices);
    }
}
