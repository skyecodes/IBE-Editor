package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.FontRenderer;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.ShapeRenderer;
import com.github.franckyi.gamehooks.impl.client.FabricFontRenderer;
import com.github.franckyi.gamehooks.impl.client.FabricShapeRenderer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;

public final class FabricClientHooks implements ClientHooks {
    public static final ClientHooks INSTANCE = new FabricClientHooks();

    private FabricClientHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public FontRenderer<?, ?> fontRenderer() {
        return FabricFontRenderer.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ShapeRenderer<?> shapeRenderer() {
        return FabricShapeRenderer.INSTANCE;
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
