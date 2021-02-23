package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.impl.common.FabricTextFactory;
import com.github.franckyi.gamehooks.util.common.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class FabricRenderer implements Renderer<MatrixStack, net.minecraft.text.Text> {
    public static final Renderer<MatrixStack, net.minecraft.text.Text> INSTANCE = new FabricRenderer();

    private FabricRenderer() {
    }

    private TextRenderer font() {
        return MinecraftClient.getInstance().textRenderer;
    }

    @Override
    public int getFontHeight(net.minecraft.text.Text text) {
        return font().fontHeight;
    }

    @Override
    public int getFontWidth(net.minecraft.text.Text text) {
        return font().getWidth(text);
    }

    @Override
    public void drawString(MatrixStack matrices, net.minecraft.text.Text text, float x, float y, int color, boolean shadow) {
        if (shadow) {
            font().drawWithShadow(matrices, text, x, y, color);
        } else {
            font().draw(matrices, text, x, y, color);
        }
    }

    @Override
    public void fillRectangle(MatrixStack matrices, int x0, int y0, int x1, int y1, int color) {
        DrawableHelper.fill(matrices, x0, y0, x1, y1, color);
    }

    @Override
    public void drawTexture(MatrixStack matrices, String id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight) {
        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(id));
        DrawableHelper.drawTexture(matrices, x, y, 0, imageX, imageY, width, height, imageHeight, imageWidth);
    }

    @Override
    public void drawTooltip(MatrixStack matrices, Text text, int x, int y) {
        MinecraftClient.getInstance().currentScreen.renderTooltip(matrices, FabricTextFactory.INSTANCE.create(text), x, y);
    }
}
