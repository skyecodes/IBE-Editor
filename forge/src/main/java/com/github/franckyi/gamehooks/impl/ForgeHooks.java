package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.Hooks;
import com.github.franckyi.gamehooks.api.Renderer;
import com.mojang.blaze3d.matrix.MatrixStack;

public final class ForgeHooks implements Hooks {
    public static final ForgeHooks INSTANCE = new ForgeHooks();

    private ForgeHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public Renderer<MatrixStack> renderer() {
        return ForgeRenderer.INSTANCE;
    }
}
