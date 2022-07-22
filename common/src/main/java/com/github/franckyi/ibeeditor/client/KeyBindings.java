package com.github.franckyi.ibeeditor.client;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public final class KeyBindings {
    private static final KeyMapping editorKey = new KeyMapping("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor");
    private static final KeyMapping nbtEditorKey = new KeyMapping("ibeeditor.key.nbt_editor", GLFW.GLFW_KEY_N, "ibeeditor");
    private static final KeyMapping snbtEditorKey = new KeyMapping("ibeeditor.key.snbt_editor", GLFW.GLFW_KEY_R, "ibeeditor");
    private static final KeyMapping vaultKey = new KeyMapping("ibeeditor.key.vault", GLFW.GLFW_KEY_J, "ibeeditor");

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
