package com.github.franckyi.ibeeditor.forge.client;

import com.github.franckyi.guapi.common.GUAPI;
import com.github.franckyi.guapi.common.Scene;
import com.github.franckyi.guapi.common.node.Label;
import com.github.franckyi.guapi.forge.ForgeRenderer;
import com.github.franckyi.guapi.forge.ForgeScreenHandler;
import com.github.franckyi.ibeeditor.common.IBEEditorCommonTest;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

public class IBEEditorForgeClient {
    public static KeyBinding keyBinding;
    public static void onClientInit(FMLClientSetupEvent event) {
        GUAPI.init(new ForgeRenderer(), new ForgeScreenHandler());
        ClientRegistry.registerKeyBinding(keyBinding = new KeyBinding(
                "ibeeditor.key.editor",
                InputMappings.Type.KEYSYM,
                GLFW.GLFW_KEY_I,
                "ibeeditor.category"
        ));
        MinecraftForge.EVENT_BUS.addListener(IBEEditorForgeClient::onClientTick);
    }

    private static void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            while (keyBinding.isPressed()) {
                IBEEditorCommonTest.openEditor("Hello World from Forge!");
            }
        }
    }
}
