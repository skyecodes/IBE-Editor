package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.client.IKeyBinding;
import org.lwjgl.glfw.GLFW;

public final class KeyBindings {
    public static IKeyBinding editorKey;
    public static IKeyBinding nbtEditorKey;
    public static IKeyBinding snbtEditorKey;
    //public static KeyBinding clipboardKey;

    public static void init() {
        editorKey = Game.getClient().registerKeyBinding("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor.category");
        nbtEditorKey = Game.getClient().registerKeyBinding("ibeeditor.key.nbt_editor", GLFW.GLFW_KEY_N, "ibeeditor.category");
        snbtEditorKey = Game.getClient().registerKeyBinding("ibeeditor.key.snbt_editor", GLFW.GLFW_KEY_R, "ibeeditor.category");
        //clipboardKey = Game.getClient().registerKeyBinding("ibeeditor.key.clipboard", GLFW.GLFW_KEY_J, "ibeeditor.category");
    }
}