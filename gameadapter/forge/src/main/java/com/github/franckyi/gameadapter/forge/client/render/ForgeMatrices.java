package com.github.franckyi.gameadapter.forge.client.render;

import com.github.franckyi.gameadapter.api.client.render.Matrices;
import com.mojang.blaze3d.matrix.MatrixStack;

public class ForgeMatrices implements Matrices {
    private static ForgeMatrices last;
    private final MatrixStack matrices;

    private ForgeMatrices(MatrixStack matrices) {
        this.matrices = matrices;
        last = this;
    }

    public static Matrices of(MatrixStack matrices) {
        return last != null && matrices == last.matrices ? last : new ForgeMatrices(matrices);
    }

    @Override
    @SuppressWarnings("unchecked")
    public MatrixStack getMatrixStack() {
        return matrices;
    }
}
