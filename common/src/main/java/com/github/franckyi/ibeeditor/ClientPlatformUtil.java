package com.github.franckyi.ibeeditor;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.KeyMapping;

public final class ClientPlatformUtil {
    @ExpectPlatform
    public static int getKeyCode(KeyMapping keyMapping) {
        throw new AssertionError();
    }
}
