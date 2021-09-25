package com.github.franckyi.guapi.api;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
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

    public static void drawString(PoseStack matrices, Component text, float x, float y, int color, boolean shadow) {
        if (shadow) {
            font().drawShadow(matrices, text, x, y, color);
        } else {
            font().draw(matrices, text, x, y, color);
        }
    }

    public static void fillRectangle(PoseStack matrices, int x0, int y0, int x1, int y1, int color) {
        GuiComponent.fill(matrices, x0, y0, x1, y1, color);
    }

    public static void drawVLine(PoseStack matrices, int x, int y0, int y1, int color) {
        fillRectangle(matrices, x, y0, x + 1, y1, color);
    }

    public static void drawHLine(PoseStack matrices, int y, int x0, int x1, int color) {
        fillRectangle(matrices, x0, y, x1, y + 1, color);
    }

    public static void drawRectangle(PoseStack matrices, int x0, int y0, int x1, int y1, int color) {
        drawHLine(matrices, y0, x0, x1 - 1, color);
        drawVLine(matrices, x1 - 1, y0, y1 - 1, color);
        drawHLine(matrices, y1 - 1, x1, x0 + 1, color);
        drawVLine(matrices, x0, y1, y0 + 1, color);
    }

    public static void drawTexture(PoseStack matrices, ResourceLocation id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight) {
        mc().getTextureManager().bind(id);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        GuiComponent.blit(matrices, x, y, 0, imageX, imageY, width, height, imageHeight, imageWidth);
    }

    public static void drawSprite(PoseStack matrices, TextureAtlasSprite sprite, int x, int y, int imageWidth, int imageHeight) {
        mc().getTextureManager().bind(sprite.atlas().location());
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiComponent.blit(matrices, x, y, 0, imageWidth, imageHeight, sprite);
    }

    public static void drawTooltip(PoseStack matrices, List<Component> text, int x, int y) {
        mc().screen.renderComponentTooltip(matrices, text, x, y);
    }

    public static void drawTooltip(PoseStack matrices, ItemStack itemStack, int x, int y) {
        mc().screen.renderComponentTooltip(matrices,
                itemStack.getTooltipLines(Minecraft.getInstance().player, TooltipFlag.Default.NORMAL), x, y);
    }

    public static void drawItem(ItemStack itemStack, int x, int y) {
        mc().getItemRenderer().renderAndDecorateFakeItem(itemStack, x, y);
    }
}
