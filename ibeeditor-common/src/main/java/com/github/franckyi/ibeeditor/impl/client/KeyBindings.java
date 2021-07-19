package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.client.KeyBinding;
import org.lwjgl.glfw.GLFW;

public final class KeyBindings {
    public static KeyBinding editorKey;
    public static KeyBinding nbtEditorKey;
    public static KeyBinding rawNbtEditorKey;
    public static KeyBinding clipboardKey;

    public static void init() {
        editorKey = Minecraft.getClient().registerKeyBinding("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor.category");
        nbtEditorKey = Minecraft.getClient().registerKeyBinding("ibeeditor.key.nbt_editor", GLFW.GLFW_KEY_N, "ibeeditor.category");
        rawNbtEditorKey = Minecraft.getClient().registerKeyBinding("ibeeditor.key.raw_nbt_editor", GLFW.GLFW_KEY_R, "ibeeditor.category");
        clipboardKey = Minecraft.getClient().registerKeyBinding("ibeeditor.key.clipboard", GLFW.GLFW_KEY_J, "ibeeditor.category");
    }
}
