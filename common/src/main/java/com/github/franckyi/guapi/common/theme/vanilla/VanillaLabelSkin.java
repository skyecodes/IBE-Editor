package com.github.franckyi.guapi.common.theme.vanilla;

import com.github.franckyi.guapi.common.GUAPI;
import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.node.Label;
import com.github.franckyi.guapi.common.skin.Skin;

public class VanillaLabelSkin implements Skin<Label> {
    public static final Skin<Label> INSTANCE = new VanillaLabelSkin();

    @Override
    public void render(Label node, RenderContext<?> ctx) {
        GUAPI.getRenderer().font().drawString(ctx.getMatrixStack(), node.getText(), node.getX(), node.getY(), 0xffffff);
    }

    @Override
    public int computeWidth(Label node) {
        return GUAPI.getRenderer().font().getFontWidth(node.getText());
    }

    @Override
    public int computeHeight(Label node) {
        return GUAPI.getRenderer().font().getFontHeight();
    }
}
