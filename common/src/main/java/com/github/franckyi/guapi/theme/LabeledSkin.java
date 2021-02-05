package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.node.Labeled;
import com.github.franckyi.guapi.util.Align;

public abstract class LabeledSkin<N extends Labeled> extends AbstractSkin<N> {
    @Override
    public void render(N node, RenderContext<?> ctx) {
        super.render(node, ctx);
        renderText(node, ctx);
    }

    @Override
    public int computeWidth(N node) {
        return font().getFontWidth(node.getText());
    }

    @Override
    public int computeHeight(N node) {
        return font().getFontHeight();
    }

    protected void renderText(N node, RenderContext<?> ctx) {
        String text = getText(node);
        int x = getTextX(node, text);
        int y = getTextY(node, text);
        int color = getTextColor(node);
        font().drawString(ctx.getMatrices(), text, x, y, color, node.hasShadow());
    }

    protected String getText(N node) {
        return font().trimToWidth(node.getText(), node.getWidth() - node.getPadding().getHorizontal());
    }

    protected int getTextX(N node, String text) {
        int textWidth = font().getFontWidth(text);
        return Align.getAlignedX(node.getTextAlign().getHorizontalAlign(), node, textWidth);
    }

    protected int getTextY(N node, String text) {
        int textHeight = font().getFontHeight();
        return Align.getAlignedY(node.getTextAlign().getVerticalAlign(), node, textHeight);
    }

    protected int getTextColor(N node) {
        return node.getColor();
    }
}
