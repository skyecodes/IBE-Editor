package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.ShapeRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class FabricShapeRenderer implements ShapeRenderer<MatrixStack> {
    public static final ShapeRenderer<?> INSTANCE = new FabricShapeRenderer();

    private FabricShapeRenderer() {
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
}
