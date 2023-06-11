package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.RenderHelper;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.base.theme.AbstractSkin;
import net.minecraft.client.gui.GuiGraphics;

public class VanillaItemViewSkin extends AbstractSkin<ItemView> {
    public static final Skin<ItemView> INSTANCE = new VanillaItemViewSkin();

    private VanillaItemViewSkin() {
    }

    @Override
    public void render(ItemView node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(node, guiGraphics, mouseX, mouseY, delta);
        var item = node.getItem();
        if (item != null) {
            int x = node.getX() + (node.getWidth() - node.getComputedWidth()) / 2;
            int y = node.getY() + (node.getHeight() - node.getComputedHeight()) / 2;
            RenderHelper.drawItem(guiGraphics, item, x, y);
            if (node.isDrawDecorations()) {
                RenderHelper.drawItemDecorations(guiGraphics, item, x, y);
            }
        }
    }

    @Override
    public void postRender(ItemView node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (node.isHovered() && node.getItem() != null && node.isDrawTooltip()) {
            RenderHelper.drawTooltip(guiGraphics, node.getItem(), mouseX, mouseY);
        }
        super.postRender(node, guiGraphics, mouseX, mouseY, delta);
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
