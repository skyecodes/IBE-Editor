package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.Group;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.base.theme.AbstractSkin;
import net.minecraft.client.gui.GuiGraphics;

public abstract class AbstractVanillaGroupSkin<N extends Group> extends AbstractSkin<N> {
    @Override
    public boolean preRender(N node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        boolean res = false;
        for (Node child : node.getChildren()) {
            res |= child.preRender(guiGraphics, mouseX, mouseY, delta);
        }
        return res;
    }

    @Override
    public void render(N node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(node, guiGraphics, mouseX, mouseY, delta);
        for (Node child : node.getChildren()) {
            child.render(guiGraphics, mouseX, mouseY, delta);
        }
    }

    @Override
    public void postRender(N node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.postRender(node, guiGraphics, mouseX, mouseY, delta);
        for (Node child : node.getChildren()) {
            child.postRender(guiGraphics, mouseX, mouseY, delta);
        }
    }
}
