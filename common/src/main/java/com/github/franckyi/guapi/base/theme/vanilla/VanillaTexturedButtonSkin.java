package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.guapi.api.RenderHelper;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaTexturedButtonSkinDelegate;
import com.mojang.blaze3d.vertex.PoseStack;

public class VanillaTexturedButtonSkin<N extends TexturedButton> extends AbstractVanillaButtonSkin<N, VanillaTexturedButtonSkinDelegate<N>> {
    public VanillaTexturedButtonSkin(N node) {
        super(node, new VanillaTexturedButtonSkinDelegate<>(node));
    }

    @Override
    public void render(N node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        if (node.getTextureId() != null) {
            RenderHelper.drawTexture(matrices, node.getTextureId(), node.getX(), node.getY(), node.getWidth(), node.getHeight(),
                    node.getImageX(), node.getImageY(), node.getImageWidth(), node.getImageHeight());
        }
        if (!node.isDrawButton() && node.isDisabled()) {
            RenderHelper.fillRectangle(matrices, node.getX(), node.getY(), node.getX() + node.getWidth(), node.getY() + node.getHeight(), Color.fromRGBA(0, 0, 0, 191));
        }
    }

    @Override
    public int computeWidth(TexturedButton node) {
        return 20;
    }
}
