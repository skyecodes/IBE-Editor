package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBindings;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.impl.client.ForgeKeyBindings;
import com.github.franckyi.gamehooks.impl.client.ForgeRenderer;
import net.minecraft.client.Minecraft;

public final class ForgeClientHooks implements ClientHooks {
    public static final ClientHooks INSTANCE = new ForgeClientHooks();

    private ForgeClientHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public Renderer<?> renderer() {
        return ForgeRenderer.INSTANCE;
    }

    @Override
    public KeyBindings keyBindings() {
        return ForgeKeyBindings.INSTANCE;
    }

    @Override
    public void unlockCursor() {
        Minecraft.getInstance().mouseHelper.ungrabMouse();
    }
}
