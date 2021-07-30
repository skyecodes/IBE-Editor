package com.github.franckyi.gameadapter.fabric.client.render;

import com.github.franckyi.gameadapter.api.client.render.Matrices;
import com.github.franckyi.gameadapter.api.client.render.Renderer;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.stream.Collectors;

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
            font().drawWithShadow(matrices.get(), (net.minecraft.text.Text) text.get(), x, y, color);
        } else {
            font().draw(matrices.get(), (net.minecraft.text.Text) text.get(), x, y, color);
        }
    }

    @Override
    public void fillRectangle(Matrices matrices, int x0, int y0, int x1, int y1, int color) {
        DrawableHelper.fill(matrices.get(), x0, y0, x1, y1, color);
    }

    @Override
    public void drawTexture(Matrices matrices, String id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight) {
        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(id));
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        DrawableHelper.drawTexture(matrices.get(), x, y, 0, imageX, imageY, width, height, imageHeight, imageWidth);
    }

    @Override
    public void drawSprite(Matrices matrices, Object spriteObj, int x, int y, int imageWidth, int imageHeight) {
        Sprite sprite = (Sprite) spriteObj;
        MinecraftClient.getInstance().getTextureManager().bindTexture(sprite.getAtlas().getId());
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        DrawableHelper.drawSprite(matrices.get(), x, y, 0, imageWidth, imageHeight, sprite);
    }

    @Override
    public void drawTooltip(Matrices matrices, List<Text> text, int x, int y) {
        MinecraftClient.getInstance().currentScreen.renderTooltip(matrices.get(), text.stream().<net.minecraft.text.Text>map(Text::get).collect(Collectors.toList()), x, y);
    }

    @Override
    public void drawTooltip(Matrices matrices, Item item, int x, int y) {
        MinecraftClient.getInstance().currentScreen.renderTooltip(matrices.get(), ((ItemStack) item.get()).getTooltip(MinecraftClient.getInstance().player, TooltipContext.Default.NORMAL), x, y);
    }

    @Override
    public void drawItem(Item item, int x, int y) {
        MinecraftClient.getInstance().getItemRenderer().renderInGui(item.get(), x, y);
    }
}
