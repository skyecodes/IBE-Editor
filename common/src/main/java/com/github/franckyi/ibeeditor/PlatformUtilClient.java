package com.github.franckyi.ibeeditor;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.KeyMapping;

public final class PlatformUtilClient {
    @ExpectPlatform
    public static KeyMapping registerKeyBinding(String name, int code, String category) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int getKeyCode(KeyMapping keyMapping) {
        throw new AssertionError();
    }
}
