package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.RenderHelper;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.base.theme.AbstractSkin;
import com.mojang.blaze3d.vertex.PoseStack;

public class VanillaItemViewSkin extends AbstractSkin<ItemView> {
    public static final Skin<ItemView> INSTANCE = new VanillaItemViewSkin();

    private VanillaItemViewSkin() {
    }

    @Override
    public void render(ItemView node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        if (node.getItem() != null) {
            RenderHelper.drawItem(node.getItem(),
                    node.getX() + (node.getWidth() - node.getComputedWidth()) / 2,
                    node.getY() + (node.getHeight() - node.getComputedHeight()) / 2);
        }
    }

    @Override
    public void postRender(ItemView node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        if (node.isHovered() && node.getItem() != null) {
            RenderHelper.drawTooltip(matrices, node.getItem(), mouseX, mouseY);
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
