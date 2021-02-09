package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.FontRenderer;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.ShapeRenderer;
import com.github.franckyi.gamehooks.impl.client.ForgeFontRenderer;
import com.github.franckyi.gamehooks.impl.client.ForgeShapeRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public final class ForgeClientHooks implements ClientHooks {
    public static final ClientHooks INSTANCE = new ForgeClientHooks();

    private ForgeClientHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public FontRenderer<?, ?> fontRenderer() {
        return ForgeFontRenderer.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ShapeRenderer<?> shapeRenderer() {
        return ForgeShapeRenderer.INSTANCE;
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
