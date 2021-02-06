package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.RendererHooks;
import com.github.franckyi.gamehooks.api.client.renderer.FontHooks;
import com.github.franckyi.gamehooks.api.client.renderer.ShapeHooks;
import com.github.franckyi.gamehooks.impl.client.renderer.FabricFontHooks;
import com.github.franckyi.gamehooks.impl.client.renderer.FabricShapeHooks;

public final class FabricRendererHooks implements RendererHooks {
    public static final RendererHooks INSTANCE = new FabricRendererHooks();

    private FabricRendererHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public FontHooks<?, ?> font() {
        return FabricFontHooks.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ShapeHooks<?> shape() {
        return FabricShapeHooks.INSTANCE;
    }
}
