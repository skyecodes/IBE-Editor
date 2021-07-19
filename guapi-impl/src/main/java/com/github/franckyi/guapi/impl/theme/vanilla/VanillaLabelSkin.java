package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.impl.theme.AbstractSkin;
import com.github.franckyi.guapi.util.Align;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.client.render.Matrices;
import com.github.franckyi.minecraft.api.client.render.Renderer;
import com.github.franckyi.minecraft.api.common.text.Text;

public class VanillaLabelSkin extends AbstractSkin<Label> {
    public static final Skin<Label> INSTANCE = new VanillaLabelSkin();

    private VanillaLabelSkin() {
    }

    @Override
    public void render(Label node, Matrices matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        renderText(node, matrices, mouseX, mouseY, delta);
    }

    protected void renderText(Label node, Matrices matrices, int mouseX, int mouseY, float delta) {
        Renderer renderer = Minecraft.getClient().getRenderer();
        Text text = node.getLabel();
        int x = Align.getAlignedX(node.getTextAlign().getHorizontalAlign(), node, renderer.getFontWidth(text));
        int y = Align.getAlignedY(node.getTextAlign().getVerticalAlign(), node, renderer.getFontHeight(text));
        renderer.drawString(matrices, text, x, y, 0xffffff, node.hasShadow());
    }

    @Override
    public int computeWidth(Label node) {
        return Minecraft.getClient().getRenderer().getFontWidth(node.getLabel());
    }

    @Override
    public int computeHeight(Label node) {
        return Minecraft.getClient().getRenderer().getFontHeight(node.getLabel()) - 1;
    }
}
