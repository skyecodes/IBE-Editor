package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.theme.LabeledSkin;
import com.github.franckyi.guapi.theme.Skin;

public class VanillaButtonSkin extends LabeledSkin<Button> {
    public static final Skin<Button> INSTANCE = new VanillaButtonSkin();

    private VanillaButtonSkin() {
    }

    @Override
    protected void renderBackground(Button node, RenderContext<?> ctx) {
        super.renderBackground(node, ctx);
        renderButton(node, ctx);
    }

    protected void renderButton(Button node, RenderContext<?> ctx) {
        widget().bindTexture();
        system().color4f(1.0f, 1.0f, 1.0f, 1.0f);
        int i = getYImage(node, ctx);
        system().enableBlend();
        system().defaultBlendFunc();
        system().enableDepthTest();
        widget().drawTexture(ctx.getMatrices(), node.getX(), node.getY(),
                0, 46 + i * 20, node.getWidth() / 2, node.getHeight());
        widget().drawTexture(ctx.getMatrices(), node.getX() + node.getWidth() / 2, node.getY(),
                200 - node.getWidth() / 2, 46 + i * 20, node.getWidth() / 2, node.getHeight());
    }

    protected int getYImage(Button node, RenderContext<?> ctx) {
        if (node.isDisabled()) {
            return 0;
        }
        if (node.inBounds(ctx.getMouseX(), ctx.getMouseY())) {
            return 2;
        }
        return 1;
    }

    @Override
    protected void renderText(Button node, RenderContext<?> ctx, String text, int x, int y) {
        super.renderText(node, ctx, text, x, y + 1);
    }

    @Override
    public int computeWidth(Button node) {
        return super.computeWidth(node) + 20;
    }

    @Override
    public int computeHeight(Button node) {
        return 20;
    }
}
