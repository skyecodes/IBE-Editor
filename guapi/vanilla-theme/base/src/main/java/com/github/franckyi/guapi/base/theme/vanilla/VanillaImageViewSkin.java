package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.client.render.Matrices;
import com.github.franckyi.guapi.api.node.ImageView;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.base.theme.AbstractSkin;

public final class VanillaImageViewSkin extends AbstractSkin<ImageView> {
    public static final Skin<ImageView> INSTANCE = new VanillaImageViewSkin();

    private VanillaImageViewSkin() {
    }

    @Override
    public void render(ImageView node, Matrices matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        Game.getClient().getRenderer().drawTexture(matrices, node.getTextureId(),
                node.getX(), node.getY(), node.getWidth(), node.getHeight(),
                node.getImageX(), node.getImageY(), node.getImageWidth(), node.getImageHeight());
    }

    @Override
    public int computeWidth(ImageView node) {
        return node.getImageWidth() + node.getPadding().getHorizontal();
    }

    @Override
    public int computeHeight(ImageView node) {
        return node.getImageHeight() + node.getPadding().getVertical();
    }
}
