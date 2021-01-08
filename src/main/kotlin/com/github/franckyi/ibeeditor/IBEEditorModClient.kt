package com.github.franckyi.ibeeditor

import com.github.franckyi.guapi.GuapiScreen
import com.github.franckyi.guapi.Label
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.options.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW


class IBEEditorModClient : ClientModInitializer {
    companion object {
        lateinit var keyBinding: KeyBinding
    }

    override fun onInitializeClient() {
        println("Hello World Client!")
        keyBinding = KeyBindingHelper.registerKeyBinding(
            KeyBinding(
                "key.ibeeditor.editor",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_I,
                "category.ibeeditor"
            )
        )
        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client ->
            if (keyBinding.wasPressed()) {
                client.openScreen(GuapiScreen(Label("Test")))
            }
        })
    }
}