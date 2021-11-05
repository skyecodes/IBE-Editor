package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.PlatformUtilClient;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public final class KeyBindings {
    public static KeyMapping editorKey;
    public static KeyMapping nbtEditorKey;
    public static KeyMapping snbtEditorKey;
    public static KeyMapping vaultKey;

    public static void register() {
        editorKey = register("editor", GLFW.GLFW_KEY_I);
        nbtEditorKey = register("nbt_editor", GLFW.GLFW_KEY_N);
        snbtEditorKey = register("snbt_editor", GLFW.GLFW_KEY_R);
        vaultKey = register("vault", GLFW.GLFW_KEY_J);
    }

    private static KeyMapping register(String name, int code) {
        return PlatformUtilClient.registerKeyBinding("ibeeditor.key." + name, code, "ibeeditor");
    }
}
