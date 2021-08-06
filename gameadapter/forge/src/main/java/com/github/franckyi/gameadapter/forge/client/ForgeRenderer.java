package com.github.franckyi.gameadapter.forge.client;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.github.franckyi.gameadapter.api.client.IRenderer;
import com.github.franckyi.gameadapter.api.client.ISprite;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.IItemStack;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.List;
import java.util.stream.Collectors;

public class ForgeRenderer implements IRenderer {
    public static final IRenderer INSTANCE = new ForgeRenderer();

    private ForgeRenderer() {
    }

    private net.minecraft.client.gui.FontRenderer font() {
        return Minecraft.getInstance().font;
    }

    @Override
    public int getFontHeight(Text text) {
        return font().lineHeight;
    }

    @Override
    public int getFontWidth(Text text) {
        return font().width((ITextComponent) text.get());
    }

    @Override
    public void drawString(IMatrices matrices, Text text, float x, float y, int color, boolean shadow) {
        if (shadow) {
            font().drawShadow((MatrixStack) matrices, (ITextComponent) text.get(), x, y, color);
        } else {
            font().draw((MatrixStack) matrices, (ITextComponent) text.get(), x, y, color);
        }
    }

    @Override
    public void fillRectangle(IMatrices matrices, int x0, int y0, int x1, int y1, int color) {
        AbstractGui.fill((MatrixStack) matrices, x0, y0, x1, y1, color);
    }

    @Override
    public void drawTexture(IMatrices matrices, IIdentifier id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight) {
        Minecraft.getInstance().getTextureManager().bind((ResourceLocation) id);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        AbstractGui.blit((MatrixStack) matrices, x, y, 0, imageX, imageY, width, height, imageHeight, imageWidth);
    }

    @Override
    public void drawSprite(IMatrices matrices, ISprite spriteObj, int x, int y, int imageWidth, int imageHeight) {
        TextureAtlasSprite sprite = (TextureAtlasSprite) spriteObj;
        Minecraft.getInstance().getTextureManager().bind(sprite.atlas().location());
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        AbstractGui.blit((MatrixStack) matrices, x, y, 0, imageWidth, imageHeight, sprite);
    }

    @Override
    public void drawTooltip(IMatrices matrices, List<Text> text, int x, int y) {
        Minecraft.getInstance().screen.renderComponentTooltip((MatrixStack) matrices, text.stream().<ITextComponent>map(Text::get).collect(Collectors.toList()), x, y);
    }

    @Override
    public void drawTooltip(IMatrices matrices, IItemStack itemStack, int x, int y) {
        Minecraft.getInstance().screen.renderComponentTooltip((MatrixStack) matrices,
                net.minecraft.item.ItemStack.class.cast(itemStack).getTooltipLines(Minecraft.getInstance().player, ITooltipFlag.TooltipFlags.NORMAL), x, y);
    }

    @Override
    public void drawItem(IItemStack itemStack, int x, int y) {
        Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(net.minecraft.item.ItemStack.class.cast(itemStack), x, y);
    }
}
