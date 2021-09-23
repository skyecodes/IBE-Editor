package com.github.franckyi.ibeeditor.fabric;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class PlatformUtilClientImpl {
    public static KeyMapping registerKeyBinding(String name, int code, String category) {
        return KeyBindingHelper.registerKeyBinding(new KeyMapping(name, code, category));
    }

    public static int getKeyCode(KeyMapping keyMapping) {
        return KeyBindingHelper.getBoundKeyOf(keyMapping).getValue();
    }
}
