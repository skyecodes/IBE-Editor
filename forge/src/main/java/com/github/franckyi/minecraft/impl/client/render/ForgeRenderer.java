package com.github.franckyi.minecraft.impl.client.render;

import com.github.franckyi.minecraft.api.client.render.Matrices;
import com.github.franckyi.minecraft.api.client.render.Renderer;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ForgeRenderer implements Renderer {
    public static final Renderer INSTANCE = new ForgeRenderer();

    private ForgeRenderer() {
    }

    private net.minecraft.client.gui.FontRenderer font() {
        return Minecraft.getInstance().fontRenderer;
    }

    @Override
    public int getFontHeight(Text text) {
        return font().FONT_HEIGHT;
    }

    @Override
    public int getFontWidth(Text text) {
        return font().getStringPropertyWidth(text.getComponent());
    }

    @Override
    public void drawString(Matrices matrices, Text text, float x, float y, int color, boolean shadow) {
        if (shadow) {
            font().drawText(matrices.getMatrixStack(), text.getComponent(), x, y, color);
        } else {
            font().drawTextWithShadow(matrices.getMatrixStack(), (ITextComponent) text.getComponent(), x, y, color);
        }
    }

    @Override
    public void fillRectangle(Matrices matrices, int x0, int y0, int x1, int y1, int color) {
        AbstractGui.fill(matrices.getMatrixStack(), x0, y0, x1, y1, color);
    }

    @Override
    public void drawTexture(Matrices matrices, String id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight) {
        Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(id));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        AbstractGui.blit(matrices.getMatrixStack(), x, y, 0, imageX, imageY, width, height, imageHeight, imageWidth);
    }

    @Override
    public void drawTooltip(Matrices matrices, Text text, int x, int y) {
        Minecraft.getInstance().currentScreen.renderTooltip(matrices.getMatrixStack(), (ITextComponent) text.getComponent(), x, y);
    }
}
