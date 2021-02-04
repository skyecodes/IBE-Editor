package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBindings;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.impl.client.ForgeKeyBindings;
import com.github.franckyi.gamehooks.impl.client.ForgeRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;

public final class ForgeClientHooks implements ClientHooks {
    public static final ForgeClientHooks INSTANCE = new ForgeClientHooks();

    private ForgeClientHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public Renderer<MatrixStack> renderer() {
        return ForgeRenderer.INSTANCE;
    }

    @Override
    public KeyBindings keyBindings() {
        return ForgeKeyBindings.INSTANCE;
    }
}
