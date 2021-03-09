package com.github.franckyi.minecraft.impl.client.render;

import com.github.franckyi.minecraft.api.client.render.Matrices;
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
