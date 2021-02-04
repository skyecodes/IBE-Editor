package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.KeyBindings;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public final class ForgeKeyBindings implements KeyBindings {
    public static final KeyBindings INSTANCE = new ForgeKeyBindings();

    private ForgeKeyBindings() {
    }

    @Override
    public KeyBinding register(String name, int keyCode, String category) {
        net.minecraft.client.settings.KeyBinding keyBinding = new net.minecraft.client.settings.KeyBinding(name, keyCode, category);
        ClientRegistry.registerKeyBinding(keyBinding);
        return keyBinding::isPressed;
    }
}
