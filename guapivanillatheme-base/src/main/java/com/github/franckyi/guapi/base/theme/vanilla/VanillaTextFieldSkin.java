package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.gameadapter.api.Color;
import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.gameadapter.api.client.IRenderer;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.base.theme.DelegatedSkin;

public class VanillaTextFieldSkin<N extends TextField> extends DelegatedSkin<N> {
    public VanillaTextFieldSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public void render(N node, IMatrices matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        if (!(node.isValidationForced() || node.getValidator().test(node.getText()))) {
            drawBorder(node, matrices, Color.fromRGBA(1, 0, 0, 0.8));
        } else if (node.isSuggested()) {
            drawBorder(node, matrices, Color.fromRGBA(0, 1, 0, 0.8));
        }
    }

    @Override
    public int computeWidth(N node) {
        return 150;
    }

    @Override
    public int computeHeight(N node) {
        return 20;
    }

    private void drawBorder(N node, IMatrices matrices, int color) {
        IRenderer.get().drawRectangle(matrices, node.getX(), node.getY(),
                node.getX() + node.getWidth(), node.getY() + node.getHeight(), color);
    }
}
