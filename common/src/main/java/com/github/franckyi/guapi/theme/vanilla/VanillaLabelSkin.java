package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.theme.AbstractSkin;
import com.github.franckyi.guapi.theme.Skin;

public class VanillaLabelSkin extends AbstractSkin<Label> {
    public static final Skin<Label> INSTANCE = new VanillaLabelSkin();

    @Override
    public void render(Label node, RenderContext<?> ctx) {
        super.render(node, ctx);
        String text = font().trimToWidth(node.getText(), node.getWidth() - node.getPadding().getHorizontal());
        int x, textWidth = font().getFontWidth(text);
        switch (node.getTextAlign()) {
            case CENTER:
                x = node.getX() + node.getPadding().getLeft() + ((node.getWidth() - node.getPadding().getHorizontal()) - textWidth) / 2;
                break;
            case RIGHT:
                x = node.getX() + node.getPadding().getLeft() + (node.getWidth() - node.getPadding().getHorizontal()) - textWidth;
                break;
            case LEFT:
            default:
                x = node.getX() + node.getPadding().getLeft();
                break;
        }
        font().drawString(ctx.getMatrixStack(), text, x, node.getY(), node.getColor(), node.hasShadow());
    }

    @Override
    public int computeWidth(Label node) {
        return font().getFontWidth(node.getText());
    }

    @Override
    public int computeHeight(Label node) {
        return font().getFontHeight();
    }
}
