package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.client.render.Matrices;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.gameadapter.Color;

public class VanillaTexturedButtonSkin<N extends TexturedButton> extends AbstractVanillaButtonSkin<N> {
    public VanillaTexturedButtonSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public void render(N node, Matrices matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        if (node.getTextureId() != null) {
            Game.getClient().getRenderer().drawTexture(matrices, node.getTextureId(), node.getX(), node.getY(), node.getWidth(), node.getHeight(),
                    node.getImageX(), node.getImageY(), node.getImageWidth(), node.getImageHeight());
        }
        if (!node.isDrawButton() && node.isDisabled()) {
            Game.getClient().getRenderer().fillRectangle(matrices, node.getX(), node.getY(), node.getX() + node.getWidth(), node.getY() + node.getHeight(), Color.fromRGBA(0, 0, 0, 191));
        }
    }

    @Override
    public int computeWidth(TexturedButton node) {
        return 20;
    }
}
