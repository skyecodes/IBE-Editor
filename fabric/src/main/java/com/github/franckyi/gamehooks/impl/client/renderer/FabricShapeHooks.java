package com.github.franckyi.gamehooks.impl.client.renderer;

import com.github.franckyi.gamehooks.api.client.renderer.ShapeHooks;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class FabricShapeHooks implements ShapeHooks<MatrixStack> {
    public static final ShapeHooks<?> INSTANCE = new FabricShapeHooks();

    private FabricShapeHooks() {
    }

    @Override
    public void fillRectangle(MatrixStack matrices, int x0, int y0, int x1, int y1, int color) {
        DrawableHelper.fill(matrices, x0, y0, x1, y1, color);
    }
}
