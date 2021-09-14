package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.api.Game;
import com.github.franckyi.gameadapter.api.client.IKeyBinding;
import org.lwjgl.glfw.GLFW;

public final class KeyBindings {
    public static IKeyBinding editorKey;
    public static IKeyBinding nbtEditorKey;
    public static IKeyBinding snbtEditorKey;
    //public static KeyBinding vaultKey;

    public static void init() {
        editorKey = register("editor", GLFW.GLFW_KEY_I);
        nbtEditorKey = register("nbt_editor", GLFW.GLFW_KEY_N);
        snbtEditorKey = register("snbt_editor", GLFW.GLFW_KEY_R);
        //vaultKey = register("vault", GLFW.GLFW_KEY_J);
    }

    private static IKeyBinding register(String name, int code) {
        return Game.getClient().registerKeyBinding("ibeeditor.key." + name, code, "ibeeditor");
    }
}
