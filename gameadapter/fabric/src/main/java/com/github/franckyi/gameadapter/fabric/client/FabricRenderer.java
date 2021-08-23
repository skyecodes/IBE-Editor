package com.github.franckyi.gameadapter.fabric.client;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.gameadapter.api.client.IRenderer;
import com.github.franckyi.gameadapter.api.client.ISprite;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.List;

public class FabricRenderer implements IRenderer {
    public static final FabricRenderer INSTANCE = new FabricRenderer();

    private FabricRenderer() {
    }

    private TextRenderer font() {
        return MinecraftClient.getInstance().textRenderer;
    }

    @Override
    public int getFontHeight(IText text) {
        return font().fontHeight;
    }

    @Override
    public int getFontWidth(IText text) {
        return font().getWidth((net.minecraft.text.Text) text);
    }

    @Override
    public void drawString(IMatrices matrices, IText text, float x, float y, int color, boolean shadow) {
        if (shadow) {
            font().drawWithShadow((MatrixStack) matrices, (net.minecraft.text.Text) text, x, y, color);
        } else {
            font().draw((MatrixStack) matrices, (net.minecraft.text.Text) text, x, y, color);
        }
    }

    @Override
    public void fillRectangle(IMatrices matrices, int x0, int y0, int x1, int y1, int color) {
        DrawableHelper.fill((MatrixStack) matrices, x0, y0, x1, y1, color);
    }

    @Override
    public void drawTexture(IMatrices matrices, IIdentifier id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight) {
        MinecraftClient.getInstance().getTextureManager().bindTexture((Identifier) id);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        DrawableHelper.drawTexture((MatrixStack) matrices, x, y, 0, imageX, imageY, width, height, imageHeight, imageWidth);
    }

    @Override
    public void drawSprite(IMatrices matrices, ISprite spriteObj, int x, int y, int imageWidth, int imageHeight) {
        Sprite sprite = (Sprite) spriteObj;
        MinecraftClient.getInstance().getTextureManager().bindTexture(sprite.getAtlas().getId());
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        DrawableHelper.drawSprite((MatrixStack) matrices, x, y, 0, imageWidth, imageHeight, sprite);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void drawTooltip(IMatrices matrices, List<IText> text, int x, int y) {
        MinecraftClient.getInstance().currentScreen.renderTooltip((MatrixStack) matrices, (List) text, x, y);
    }

    @Override
    public void drawTooltip(IMatrices matrices, IItemStack itemStack, int x, int y) {
        MinecraftClient.getInstance().currentScreen.renderTooltip((MatrixStack) matrices, net.minecraft.item.ItemStack.class.cast(itemStack).getTooltip(MinecraftClient.getInstance().player, TooltipContext.Default.NORMAL), x, y);
    }

    @Override
    public void drawItem(IItemStack itemStack, int x, int y) {
        MinecraftClient.getInstance().getItemRenderer().renderInGui(net.minecraft.item.ItemStack.class.cast(itemStack), x, y);
    }
}
