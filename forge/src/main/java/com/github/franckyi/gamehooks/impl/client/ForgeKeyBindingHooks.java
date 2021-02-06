package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.KeyBindingHooks;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public final class ForgeKeyBindingHooks implements KeyBindingHooks {
    public static final KeyBindingHooks INSTANCE = new ForgeKeyBindingHooks();

    private ForgeKeyBindingHooks() {
    }

    @Override
    public KeyBinding register(String name, int keyCode, String category) {
        net.minecraft.client.settings.KeyBinding keyBinding = new net.minecraft.client.settings.KeyBinding(name, keyCode, category);
        ClientRegistry.registerKeyBinding(keyBinding);
        return keyBinding::isPressed;
    }
}
