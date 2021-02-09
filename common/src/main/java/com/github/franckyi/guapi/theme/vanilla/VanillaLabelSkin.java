package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.theme.AbstractSkin;
import com.github.franckyi.guapi.theme.ProvidedSkin;
import com.github.franckyi.guapi.theme.Skin;
import com.github.franckyi.guapi.util.Align;

public class VanillaLabelSkin extends AbstractSkin<Label> {
    public static final Skin<Label> INSTANCE = new VanillaLabelSkin();

    private VanillaLabelSkin() {
    }

    @Override
    public void render(Label node, Object matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        Object text = node.getLabelComponent();
        int x = Align.getAlignedX(node.getTextAlign().getHorizontalAlign(), node, font().getFontWidth(text));
        int y = Align.getAlignedY(node.getTextAlign().getVerticalAlign(), node, font().getFontHeight(text));
        font().drawString(matrices, text, x, y, 0xffffff, node.hasShadow());
    }

    @Override
    public int computeWidth(Label node) {
        return font().getFontWidth(node.getLabelComponent());
    }

    @Override
    public int computeHeight(Label node) {
        return font().getFontHeight(node.getLabelComponent());
    }
}
