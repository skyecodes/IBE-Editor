package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.impl.common.ForgeTextFactory;
import com.github.franckyi.gamehooks.util.common.text.Text;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ForgeRenderer implements Renderer<MatrixStack, ITextComponent> {
    public static final Renderer<MatrixStack, ITextComponent> INSTANCE = new ForgeRenderer();

    private ForgeRenderer() {
    }

    private net.minecraft.client.gui.FontRenderer font() {
        return Minecraft.getInstance().fontRenderer;
    }

    @Override
    public int getFontHeight(ITextComponent text) {
        return font().FONT_HEIGHT;
    }

    @Override
    public int getFontWidth(ITextComponent text) {
        return font().getStringPropertyWidth(text);
    }

    @Override
    public void drawString(MatrixStack matrixStack, ITextComponent text, float x, float y, int color, boolean shadow) {
        if (shadow) {
            font().func_243246_a(matrixStack, text, x, y, color);
        } else {
            font().func_243248_b(matrixStack, text, x, y, color);
        }
    }

    @Override
    public void fillRectangle(MatrixStack matrices, int x0, int y0, int x1, int y1, int color) {
        AbstractGui.fill(matrices, x0, y0, x1, y1, color);
    }

    @Override
    public void drawTexture(MatrixStack matrices, String id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight) {
        Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(id));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        AbstractGui.blit(matrices, x, y, 0, imageX, imageY, width, height, imageHeight, imageWidth);
    }

    @Override
    public void drawTooltip(MatrixStack matrices, Text text, int x, int y) {
        Minecraft.getInstance().currentScreen.renderTooltip(matrices, ForgeTextFactory.INSTANCE.create(text), x, y);
    }
}
