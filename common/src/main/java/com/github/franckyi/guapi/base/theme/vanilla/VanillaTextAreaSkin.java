package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.guapi.api.RenderHelper;
import com.github.franckyi.guapi.api.node.TextArea;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaTextAreaSkinDelegate;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;

public class VanillaTextAreaSkin extends AbstractVanillaWidgetSkin<TextArea, VanillaTextAreaSkinDelegate<TextArea>> {
    public VanillaTextAreaSkin(TextArea node) {
        this(node, new VanillaTextAreaSkinDelegate<>(node));
    }

    protected VanillaTextAreaSkin(TextArea node, VanillaTextAreaSkinDelegate<TextArea> widget) {
        super(node, widget);
    }

    @Override
    public void render(TextArea node, GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(node, guiGraphics, mouseX, mouseY, delta);
        if (!(node.isValidationForced() || node.getValidator().test(node.getText()))) {
            drawBorder(node, guiGraphics, Color.fromRGBA(1, 0, 0, 0.8));
        } else if (node.isSuggested()) {
            drawBorder(node, guiGraphics, Color.fromRGBA(0, 1, 0, 0.8));
        }
    }

    @Override
    public int computeWidth(TextArea node) {
        return 150;
    }

    @Override
    public int computeHeight(TextArea node) {
        return 20;
    }

    private void drawBorder(TextArea node, GuiGraphics guiGraphics, int color) {
        RenderHelper.drawRectangle(guiGraphics, node.getX(), node.getY(),
                node.getX() + node.getWidth(), node.getY() + node.getHeight(), color);
    }
}
