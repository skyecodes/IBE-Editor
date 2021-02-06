package com.github.franckyi.gamehooks.impl.client.renderer;

import com.github.franckyi.gamehooks.api.client.renderer.ShapeHooks;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.AbstractGui;

public class ForgeShapeHooks implements ShapeHooks<MatrixStack> {
    public static final ShapeHooks<?> INSTANCE = new ForgeShapeHooks();

    private ForgeShapeHooks() {
    }

    @Override
    public void fillRectangle(MatrixStack matrices, int x0, int y0, int x1, int y1, int color) {
        AbstractGui.fill(matrices, x0, y0, x1, y1, color);
    }
}
