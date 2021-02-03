package com.github.franckyi.ibeeditor.fabric.client;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.impl.FabricHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.hooks.impl.FabricScreenHandler;
import com.github.franckyi.ibeeditor.common.IBEEditorCommonTest;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class IBEEditorFabricClient implements ClientModInitializer {
    public static KeyBinding keyBinding;
    @Override
    public void onInitializeClient() {
        GameHooks.init(FabricHooks.INSTANCE);
        GUAPI.init(FabricScreenHandler.INSTANCE);
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "ibeeditor.key.editor",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_I,
                "ibeeditor.category"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                MinecraftClient.getInstance().mouse.unlockCursor();
                IBEEditorCommonTest.openEditor("Hello World from Fabric!");
            }
        });
    }
}
