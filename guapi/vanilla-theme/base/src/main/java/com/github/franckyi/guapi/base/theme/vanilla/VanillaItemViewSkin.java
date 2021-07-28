package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.client.render.Matrices;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.base.theme.AbstractSkin;

public final class VanillaItemViewSkin extends AbstractSkin<ItemView> {
    public static final Skin<ItemView> INSTANCE = new VanillaItemViewSkin();

    private VanillaItemViewSkin() {
    }

    @Override
    public void render(ItemView node, Matrices matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        if (node.getItem() != null) {
            Game.getClient().getRenderer().drawItem(node.getItem(),
                    node.getX() + (node.getWidth() - node.getComputedWidth()) / 2,
                    node.getY() + (node.getHeight() - node.getComputedHeight()) / 2);
        }
    }

    @Override
    public void postRender(ItemView node, Matrices matrices, int mouseX, int mouseY, float delta) {
        if (node.isHovered() && node.getItem() != null) {
            Game.getClient().getRenderer().drawTooltip(matrices, node.getItem(), mouseX, mouseY);
        }
        super.postRender(node, matrices, mouseX, mouseY, delta);
    }

    @Override
    public int computeWidth(ItemView node) {
        return 16;
    }

    @Override
    public int computeHeight(ItemView node) {
        return 16;
    }
}
