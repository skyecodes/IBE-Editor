package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.gameadapter.api.client.IRenderer;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.base.theme.AbstractSkin;

public class VanillaItemViewSkin extends AbstractSkin<ItemView> {
    public static final Skin<ItemView> INSTANCE = new VanillaItemViewSkin();

    private VanillaItemViewSkin() {
    }

    @Override
    public void render(ItemView node, IMatrices matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        if (node.getItem() != null) {
            IRenderer.get().drawItem(node.getItem(),
                    node.getX() + (node.getWidth() - node.getComputedWidth()) / 2,
                    node.getY() + (node.getHeight() - node.getComputedHeight()) / 2);
        }
    }

    @Override
    public void postRender(ItemView node, IMatrices matrices, int mouseX, int mouseY, float delta) {
        if (node.isHovered() && node.getItem() != null) {
            IRenderer.get().drawTooltip(matrices, node.getItem(), mouseX, mouseY);
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
