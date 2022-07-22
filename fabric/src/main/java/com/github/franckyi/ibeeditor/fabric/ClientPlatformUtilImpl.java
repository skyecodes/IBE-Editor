package com.github.franckyi.ibeeditor.fabric;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class ClientPlatformUtilImpl {
    public static int getKeyCode(KeyMapping keyMapping) {
        return KeyBindingHelper.getBoundKeyOf(keyMapping).getValue();
    }
}
