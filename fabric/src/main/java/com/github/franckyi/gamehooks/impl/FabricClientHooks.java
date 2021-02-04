package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBindings;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.impl.client.FabricKeyBindings;
import com.github.franckyi.gamehooks.impl.client.FabricRenderer;
import net.minecraft.client.util.math.MatrixStack;

public final class FabricClientHooks implements ClientHooks {
    public static final FabricClientHooks INSTANCE = new FabricClientHooks();

    private FabricClientHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public Renderer<MatrixStack> renderer() {
        return FabricRenderer.INSTANCE;
    }

    @Override
    public KeyBindings keyBindings() {
        return FabricKeyBindings.INSTANCE;
    }
}
