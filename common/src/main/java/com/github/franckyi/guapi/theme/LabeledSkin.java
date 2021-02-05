package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.node.Labeled;
import com.github.franckyi.guapi.util.Align;

public abstract class LabeledSkin<T extends Labeled> extends AbstractSkin<T> {
    @Override
    public void render(T node, RenderContext<?> ctx) {
        super.render(node, ctx);
        renderText(node, ctx);
    }

    @Override
    public int computeWidth(T node) {
        return font().getFontWidth(node.getText());
    }

    @Override
    public int computeHeight(T node) {
        return font().getFontHeight();
    }

    protected void renderText(T node, RenderContext<?> ctx) {
        String text = font().trimToWidth(node.getText(), node.getWidth() - node.getPadding().getHorizontal());
        int textWidth = font().getFontWidth(text);
        int textHeight = font().getFontHeight();
        int x = Align.getAlignedX(node.getTextAlign().getHorizontalAlign(), node, textWidth);
        int y = Align.getAlignedY(node.getTextAlign().getVerticalAlign(), node, textHeight);
        renderText(node, ctx, text, x, y);
    }

    protected void renderText(T node, RenderContext<?> ctx, String text, int x, int y) {
        font().drawString(ctx.getMatrices(), text, x, y, node.getColor().getValue(), node.hasShadow());
    }
}
