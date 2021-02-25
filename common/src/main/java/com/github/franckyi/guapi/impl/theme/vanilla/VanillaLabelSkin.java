package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.Matrices;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.api.common.Text;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.theme.Skin;
import com.github.franckyi.guapi.impl.theme.AbstractSkin;
import com.github.franckyi.guapi.util.Align;

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
        Renderer renderer = GameHooks.client().getRenderer();
        Text text = node.getLabel();
        int x = Align.getAlignedX(node.getTextAlign().getHorizontalAlign(), node, renderer.getFontWidth(text));
        int y = Align.getAlignedY(node.getTextAlign().getVerticalAlign(), node, renderer.getFontHeight(text));
        renderer.drawString(matrices, text, x, y, 0xffffff, node.hasShadow());
    }

    @Override
    public int computeWidth(Label node) {
        return GameHooks.client().getRenderer().getFontWidth(node.getLabel());
    }

    @Override
    public int computeHeight(Label node) {
        return GameHooks.client().getRenderer().getFontHeight(node.getLabel()) - 1;
    }
}
