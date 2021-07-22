package com.github.franckyi.guapi.fabric.theme.vanilla;

import com.github.franckyi.guapi.api.node.TexturedButton;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class FabricVanillaTexturedButtonRenderer extends ButtonWidget implements FabricVanillaDelegateRenderer {
    private final TexturedButton node;

    public FabricVanillaTexturedButtonRenderer(TexturedButton node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.tooltipProperty().hasValue() ? node.getTooltip().get() : LiteralText.EMPTY, button -> {
        });
        this.node = node;
        initNode(node, this);
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float delta) {
        if (node.isDrawButton()) {
            super.renderButton(matrixStack, mouseX, mouseY, delta);
        }
    }
}
