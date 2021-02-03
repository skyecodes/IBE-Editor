package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.Hooks;
import com.github.franckyi.gamehooks.api.Renderer;
import net.minecraft.client.util.math.MatrixStack;

public final class FabricHooks implements Hooks {
    public static final FabricHooks INSTANCE = new FabricHooks();

    private FabricHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public Renderer<MatrixStack> renderer() {
        return FabricRenderer.INSTANCE;
    }
}
