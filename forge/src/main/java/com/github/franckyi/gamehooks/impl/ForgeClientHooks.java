package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.RendererHooks;
import com.github.franckyi.gamehooks.impl.client.ForgeRendererHooks;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public final class ForgeClientHooks implements ClientHooks {
    public static final ClientHooks INSTANCE = new ForgeClientHooks();

    private ForgeClientHooks() {
    }

    @Override
    public RendererHooks renderer() {
        return ForgeRendererHooks.INSTANCE;
    }

    @Override
    public KeyBinding registerKeyBinding(String name, int keyCode, String category) {
        net.minecraft.client.settings.KeyBinding keyBinding = new net.minecraft.client.settings.KeyBinding(name, keyCode, category);
        ClientRegistry.registerKeyBinding(keyBinding);
        return keyBinding::isPressed;
    }

    @Override
    public void unlockCursor() {
        Minecraft.getInstance().mouseHelper.ungrabMouse();
    }
}
