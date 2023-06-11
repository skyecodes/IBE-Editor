package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.TexturedToggleButton;
import net.minecraft.client.gui.GuiGraphics;

public class VanillaTexturedToggleButtonSkin<N extends TexturedToggleButton> extends VanillaTexturedButtonSkin<N> implements VanillaToggleSkin<N> {
    public VanillaTexturedToggleButtonSkin(N node) {
        super(node);
    }

    @Override
    public void render(N node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(node, guiGraphics, mouseX, mouseY, delta);
        renderToggle(node, guiGraphics);
    }
}
