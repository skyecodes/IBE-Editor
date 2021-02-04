package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.KeyBindings;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public final class FabricKeyBindings implements KeyBindings {
    public static final KeyBindings INSTANCE = new FabricKeyBindings();

    private FabricKeyBindings() {
    }

    @Override
    public KeyBinding register(String name, int keyCode, String category) {
        net.minecraft.client.options.KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(
                new net.minecraft.client.options.KeyBinding(name, keyCode, category));
        return keyBinding::wasPressed;
    }
}
