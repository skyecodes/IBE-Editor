package com.github.franckyi.ibeeditor.forge;

import net.minecraft.client.KeyMapping;

public class ClientPlatformUtilImpl {
    public static int getKeyCode(KeyMapping keyMapping) {
        return keyMapping.getKey().getValue();
    }
}
