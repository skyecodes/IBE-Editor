package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.FontRenderer;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.impl.theme.AbstractSkin;
import com.github.franckyi.guapi.util.Align;

public class VanillaLabelSkin<M> extends AbstractSkin<Label> {
    public static final Skin<Label> INSTANCE = new VanillaLabelSkin<>();

    private VanillaLabelSkin() {
    }

    @Override
    public void render(Label node, Object matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        FontRenderer<Object, Object> fontRenderer = GameHooks.client().fontRenderer();
        Object text = node.getLabelComponent();
        int x = Align.getAlignedX(node.getTextAlign().getHorizontalAlign(), node, fontRenderer.getFontWidth(text));
        int y = Align.getAlignedY(node.getTextAlign().getVerticalAlign(), node, fontRenderer.getFontHeight(text));
        fontRenderer.drawString(matrices, text, x, y, 0xffffff, node.hasShadow());
    }

    @Override
    public int computeWidth(Label node) {
        return GameHooks.client().fontRenderer().getFontWidth(node.getLabelComponent());
    }

    @Override
    public int computeHeight(Label node) {
        return GameHooks.client().fontRenderer().getFontHeight(node.getLabelComponent());
    }
}
