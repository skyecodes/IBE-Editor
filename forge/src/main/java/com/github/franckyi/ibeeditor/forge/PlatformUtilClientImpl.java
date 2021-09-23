package com.github.franckyi.ibeeditor.forge;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class PlatformUtilClientImpl {
    public static KeyMapping registerKeyBinding(String name, int code, String category) {
        KeyMapping keyMapping = new KeyMapping(name, code, category);
        ClientRegistry.registerKeyBinding(keyMapping);
        return keyMapping;
    }

    public static int getKeyCode(KeyMapping keyMapping) {
        return keyMapping.getKey().getValue();
    }
}
