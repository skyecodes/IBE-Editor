package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.KeyBindingHooks;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public final class FabricKeyBindingHooks implements KeyBindingHooks {
    public static final KeyBindingHooks INSTANCE = new FabricKeyBindingHooks();

    private FabricKeyBindingHooks() {
    }

    @Override
    public KeyBinding register(String name, int keyCode, String category) {
        net.minecraft.client.options.KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(
                new net.minecraft.client.options.KeyBinding(name, keyCode, category));
        return keyBinding::wasPressed;
    }
}
