package com.github.franckyi.guapi.api;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public final class RenderHelper {
    private static Minecraft mc() {
        return Minecraft.getInstance();
    }

    private static Font font() {
        return mc().font;
    }

    public static int getFontHeight() {
        return font().lineHeight;
    }

    public static int getFontWidth(Component text) {
        return font().width(text);
    }

    public static void drawString(GuiGraphics guiGraphics, Component text, float x, float y, int color, boolean shadow) {
        guiGraphics.drawString(font(), text, (int) x, (int) y, color, shadow);
    }

    public static void fillRectangle(GuiGraphics guiGraphics, int x0, int y0, int x1, int y1, int color) {
        guiGraphics.fill(x0, y0, x1, y1, color);
    }

    public static void drawVLine(GuiGraphics guiGraphics, int x, int y0, int y1, int color) {
        fillRectangle(guiGraphics, x, y0, x + 1, y1, color);
    }

    public static void drawHLine(GuiGraphics guiGraphics, int y, int x0, int x1, int color) {
        fillRectangle(guiGraphics, x0, y, x1, y + 1, color);
    }

    public static void drawRectangle(GuiGraphics guiGraphics, int x0, int y0, int x1, int y1, int color) {
        drawHLine(guiGraphics, y0, x0, x1 - 1, color);
        drawVLine(guiGraphics, x1 - 1, y0, y1 - 1, color);
        drawHLine(guiGraphics, y1 - 1, x1, x0 + 1, color);
        drawVLine(guiGraphics, x0, y1, y0 + 1, color);
    }

    public static void drawTexture(GuiGraphics guiGraphics, ResourceLocation id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight) {
        RenderSystem.setShaderTexture(0, id);
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        guiGraphics.blit(id, x, y, 0, imageX, imageY, width, height, imageWidth, imageHeight);
    }

    public static void drawSprite(GuiGraphics guiGraphics, TextureAtlasSprite sprite, int x, int y, int imageWidth, int imageHeight) {
        RenderSystem.setShaderTexture(0, sprite.atlasLocation());
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(x, y, 0, imageWidth, imageHeight, sprite);
    }

    public static void drawTooltip(GuiGraphics guiGraphics, List<Component> text, int x, int y) {
        guiGraphics.renderComponentTooltip(font(), text, x, y);
    }

    public static void drawTooltip(GuiGraphics guiGraphics, ItemStack itemStack, int x, int y) {
        guiGraphics.renderComponentTooltip(font(),
                itemStack.getTooltipLines(Minecraft.getInstance().player, TooltipFlag.Default.NORMAL), x, y);
    }

    public static void drawItem(GuiGraphics guiGraphics, ItemStack itemStack, int x, int y) {
        guiGraphics.renderFakeItem(itemStack, x, y);
    }

    public static void drawItemDecorations(GuiGraphics guiGraphics, ItemStack itemStack, int x, int y) {
        guiGraphics.renderItemDecorations(font(), itemStack, x, y);
    }
}
