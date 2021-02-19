package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.ShapeRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class ForgeShapeRenderer implements ShapeRenderer<MatrixStack> {
    public static final ShapeRenderer<?> INSTANCE = new ForgeShapeRenderer();

    private ForgeShapeRenderer() {
    }

    @Override
    public void fillRectangle(MatrixStack matrices, int x0, int y0, int x1, int y1, int color) {
        AbstractGui.fill(matrices, x0, y0, x1, y1, color);
    }

    @Override
    public void drawTexture(MatrixStack matrices, String id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight) {
        Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(id));
        AbstractGui.blit(matrices, x, y, 0, imageX, imageY, width, height, imageHeight, imageWidth);
    }
}
