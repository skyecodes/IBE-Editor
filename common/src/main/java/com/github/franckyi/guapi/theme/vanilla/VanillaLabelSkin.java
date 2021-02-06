package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.theme.LabeledSkin;
import com.github.franckyi.guapi.theme.Skin;
import com.github.franckyi.guapi.util.Align;

public class VanillaLabelSkin extends LabeledSkin<Label> {
    public static final Skin<Label> INSTANCE = new VanillaLabelSkin();

    private VanillaLabelSkin() {
    }

    @Override
    public void render(Label node, RenderContext<?> ctx) {
        super.render(node, ctx);
        renderText(node, ctx);
    }

    protected void renderText(Label node, RenderContext<?> ctx) {
        String text = getText(node);
        int x = getTextX(node, text);
        int y = getTextY(node, text);
        int color = getTextColor(node);
        font().drawString(ctx.getMatrices(), text().getLiteralText(text), x, y, color, node.hasShadow());
    }

    protected String getText(Label node) {
        return font().trimToWidth(node.getText(), node.getWidth() - node.getPadding().getHorizontal());
    }

    protected int getTextX(Label node, String text) {
        int textWidth = font().getFontWidth(text);
        return Align.getAlignedX(node.getTextAlign().getHorizontalAlign(), node, textWidth);
    }

    protected int getTextY(Label node, String text) {
        int textHeight = font().getFontHeight();
        return Align.getAlignedY(node.getTextAlign().getVerticalAlign(), node, textHeight);
    }

    protected int getTextColor(Label node) {
        return node.getColor();
    }

}
