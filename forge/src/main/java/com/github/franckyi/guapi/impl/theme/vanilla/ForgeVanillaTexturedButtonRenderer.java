package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.gamehooks.impl.client.ForgeShapeRenderer;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.theme.vanilla.ForgeVanillaDelegateRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class ForgeVanillaTexturedButtonRenderer extends Button implements ForgeVanillaDelegateRenderer {
    private final TexturedButton node;

    public ForgeVanillaTexturedButtonRenderer(TexturedButton node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), new StringTextComponent(""), button -> {
        });
        this.node = node;
        active = !node.isDisabled();
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.disabledProperty().addListener(newVal -> active = !newVal);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        int x = node.getX() + (node.getWidth() - 16) / 2;
        int y = node.getY() + (node.getHeight() - 16) / 2;
        ForgeShapeRenderer.INSTANCE.drawTexture(matrices, node.getTextureId(), x, y, node.getWidth(), node.getHeight(),
                node.getImageX(), node.getImageY(), node.getImageWidth(), node.getImageHeight());
        if (!node.isDrawButton() && node.isDisabled()) {
            ForgeShapeRenderer.INSTANCE.fillRectangle(matrices, x, y, x + node.getWidth(), y + node.getHeight(), 0x4f000000);
        }
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (node.isDrawButton()) {
            super.renderButton(matrices, mouseX, mouseY, delta);
        }
    }
}
