package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBindingHooks;
import com.github.franckyi.gamehooks.api.client.RendererHooks;
import com.github.franckyi.gamehooks.impl.client.FabricKeyBindingHooks;
import com.github.franckyi.gamehooks.impl.client.FabricRendererHooks;
import net.minecraft.client.MinecraftClient;

public final class FabricClientHooks implements ClientHooks {
    public static final ClientHooks INSTANCE = new FabricClientHooks();

    private FabricClientHooks() {
    }

    @Override
    public RendererHooks renderer() {
        return FabricRendererHooks.INSTANCE;
    }

    @Override
    public KeyBindingHooks keyBindings() {
        return FabricKeyBindingHooks.INSTANCE;
    }

    @Override
    public void unlockCursor() {
        MinecraftClient.getInstance().mouse.unlockCursor();
    }
}
