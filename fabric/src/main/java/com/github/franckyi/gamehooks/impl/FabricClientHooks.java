package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.RendererHooks;
import com.github.franckyi.gamehooks.impl.client.FabricRendererHooks;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
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
    public KeyBinding registerKeyBinding(String name, int keyCode, String category) {
        net.minecraft.client.options.KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(
                new net.minecraft.client.options.KeyBinding(name, keyCode, category));
        return keyBinding::wasPressed;
    }

    @Override
    public void unlockCursor() {
        MinecraftClient.getInstance().mouse.unlockCursor();
    }
}
