package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.impl.theme.AbstractSkin;
import com.github.franckyi.guapi.util.Align;

public class VanillaLabelSkin extends AbstractSkin<Label> {
    public static final Skin<Label> INSTANCE = new VanillaLabelSkin();

    private VanillaLabelSkin() {
    }

    @Override
    public <M> void render(Label node, M matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        renderText(node, matrices, mouseX, mouseY, delta);
    }

    protected <M, T> void renderText(Label node, M matrices, int mouseX, int mouseY, float delta) {
        Renderer<M, T> renderer = GameHooks.client().getRenderer();
        T text = node.getLabelComponent();
        int x = Align.getAlignedX(node.getTextAlign().getHorizontalAlign(), node, renderer.getFontWidth(text));
        int y = Align.getAlignedY(node.getTextAlign().getVerticalAlign(), node, renderer.getFontHeight(text));
        renderer.drawString(matrices, text, x, y, 0xffffff, node.hasShadow());
    }

    @Override
    public int computeWidth(Label node) {
        return GameHooks.client().getRenderer().getFontWidth(node.getLabelComponent());
    }

    @Override
    public int computeHeight(Label node) {
        return GameHooks.client().getRenderer().getFontHeight(node.getLabelComponent()) - 1;
    }
}
