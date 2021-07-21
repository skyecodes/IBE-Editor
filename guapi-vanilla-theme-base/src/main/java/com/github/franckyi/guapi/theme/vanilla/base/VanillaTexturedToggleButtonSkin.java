package com.github.franckyi.guapi.theme.vanilla.base;

import com.github.franckyi.gameadapter.api.client.render.Matrices;
import com.github.franckyi.guapi.api.node.TexturedToggleButton;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;

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
