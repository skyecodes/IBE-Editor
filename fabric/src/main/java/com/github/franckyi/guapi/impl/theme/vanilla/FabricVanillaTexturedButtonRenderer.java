package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.gamehooks.impl.client.FabricShapeRenderer;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.theme.vanilla.FabricVanillaDelegateRenderer;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class FabricVanillaTexturedButtonRenderer extends ButtonWidget implements FabricVanillaDelegateRenderer {
    private final TexturedButton node;

    public FabricVanillaTexturedButtonRenderer(TexturedButton node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), new LiteralText(""), button -> {
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
        FabricShapeRenderer.INSTANCE.drawTexture(matrices, node.getTextureId(), x, y, node.getWidth(), node.getHeight(),
                node.getImageX(), node.getImageY(), node.getImageWidth(), node.getImageHeight());
        if (!node.isDrawButton() && node.isDisabled()) {
            FabricShapeRenderer.INSTANCE.fillRectangle(matrices, x, y, x + node.getWidth(), y + node.getHeight(), 0xbf000000);
        }
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (node.isDrawButton()) {
            super.renderButton(matrices, mouseX, mouseY, delta);
        }
    }
}
