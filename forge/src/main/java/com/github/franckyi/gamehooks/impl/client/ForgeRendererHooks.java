package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.RendererHooks;
import com.github.franckyi.gamehooks.api.client.renderer.FontHooks;
import com.github.franckyi.gamehooks.api.client.renderer.ShapeHooks;
import com.github.franckyi.gamehooks.impl.client.renderer.ForgeFontHooks;
import com.github.franckyi.gamehooks.impl.client.renderer.ForgeShapeHooks;

public final class ForgeRendererHooks implements RendererHooks {
    public static final RendererHooks INSTANCE = new ForgeRendererHooks();

    private ForgeRendererHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public FontHooks<?, ?> font() {
        return ForgeFontHooks.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ShapeHooks<?> shape() {
        return ForgeShapeHooks.INSTANCE;
    }
}
