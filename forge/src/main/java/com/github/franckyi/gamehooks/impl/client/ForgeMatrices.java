package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.Matrices;
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
