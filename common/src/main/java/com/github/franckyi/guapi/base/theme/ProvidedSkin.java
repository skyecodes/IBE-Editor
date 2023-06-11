package com.github.franckyi.guapi.base.theme;

import com.github.franckyi.guapi.base.node.AbstractNode;
import net.minecraft.client.gui.GuiGraphics;

public abstract class ProvidedSkin<N extends AbstractNode> extends AbstractSkin<N> {
    private final N node;

    protected ProvidedSkin(N node) {
        this.node = node;
    }

    @Override
    public void render(N node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(node, guiGraphics, mouseX, mouseY, delta);
        renderNode(guiGraphics, mouseX, mouseY, delta);
    }

    protected abstract <M> void renderNode(M matrices, int mouseX, int mouseY, float delta);

    public N getNode() {
        return node;
    }
}
