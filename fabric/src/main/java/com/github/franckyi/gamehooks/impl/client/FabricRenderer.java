package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.Matrices;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.api.common.Text;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.util.Identifier;

public class FabricRenderer implements Renderer {
    public static final FabricRenderer INSTANCE = new FabricRenderer();

    private FabricRenderer() {
    }

    private TextRenderer font() {
        return MinecraftClient.getInstance().textRenderer;
    }

    @Override
    public int getFontHeight(Text text) {
        return font().fontHeight;
    }

    @Override
    public int getFontWidth(Text text) {
        return font().getWidth((net.minecraft.text.Text) text.get());
    }

    @Override
    public void drawString(Matrices matrices, Text text, float x, float y, int color, boolean shadow) {
        if (shadow) {
            font().drawWithShadow(matrices.getMatrixStack(), (net.minecraft.text.Text) text.get(), x, y, color);
        } else {
            font().draw(matrices.getMatrixStack(), (net.minecraft.text.Text) text.get(), x, y, color);
        }
    }

    @Override
    public void fillRectangle(Matrices matrices, int x0, int y0, int x1, int y1, int color) {
        DrawableHelper.fill(matrices.getMatrixStack(), x0, y0, x1, y1, color);
    }

    @Override
    public void drawTexture(Matrices matrices, String id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight) {
        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(id));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        DrawableHelper.drawTexture(matrices.getMatrixStack(), x, y, 0, imageX, imageY, width, height, imageHeight, imageWidth);
    }

    @Override
    public void drawTooltip(Matrices matrices, Text text, int x, int y) {
        MinecraftClient.getInstance().currentScreen.renderTooltip(matrices.getMatrixStack(), (net.minecraft.text.Text) text.get(), x, y);
    }
}
