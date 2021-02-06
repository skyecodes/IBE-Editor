package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBindingHooks;
import com.github.franckyi.gamehooks.api.client.RendererHooks;
import com.github.franckyi.gamehooks.impl.client.ForgeKeyBindingHooks;
import com.github.franckyi.gamehooks.impl.client.ForgeRendererHooks;
import net.minecraft.client.Minecraft;

public final class ForgeClientHooks implements ClientHooks {
    public static final ClientHooks INSTANCE = new ForgeClientHooks();

    private ForgeClientHooks() {
    }

    @Override
    public RendererHooks renderer() {
        return ForgeRendererHooks.INSTANCE;
    }

    @Override
    public KeyBindingHooks keyBindings() {
        return ForgeKeyBindingHooks.INSTANCE;
    }

    @Override
    public void unlockCursor() {
        Minecraft.getInstance().mouseHelper.ungrabMouse();
    }
}
