package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.ClientPlatformUtil;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public final class KeyBindings {
    private static KeyMapping editorKey;
    private static KeyMapping nbtEditorKey;
    private static KeyMapping snbtEditorKey;
    private static KeyMapping vaultKey;

    public static void init() {
        editorKey = register("editor", GLFW.GLFW_KEY_I);
        nbtEditorKey = register("nbt_editor", GLFW.GLFW_KEY_N);
        snbtEditorKey = register("snbt_editor", GLFW.GLFW_KEY_R);
        vaultKey = register("vault", GLFW.GLFW_KEY_J);
    }

    private static KeyMapping register(String name, int code) {
        return ClientPlatformUtil.registerKeyBinding("ibeeditor.key." + name, code, "ibeeditor");
    }

    public static KeyMapping getEditorKey() {
        return editorKey;
    }

    public static KeyMapping getNBTEditorKey() {
        return nbtEditorKey;
    }

    public static KeyMapping getSNBTEditorKey() {
        return snbtEditorKey;
    }

    public static KeyMapping getVaultKey() {
        return vaultKey;
    }
}
