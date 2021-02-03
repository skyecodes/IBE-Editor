package com.github.franckyi.ibeeditor.forge.client;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.impl.ForgeHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.hooks.impl.ForgeScreenHandler;
import com.github.franckyi.ibeeditor.common.IBEEditorCommonTest;
import net.minecraft.client.Minecraft;
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
        GameHooks.init(ForgeHooks.INSTANCE);
        GUAPI.init(ForgeScreenHandler.INSTANCE);
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
                Minecraft.getInstance().mouseHelper.ungrabMouse();
                IBEEditorCommonTest.openEditor("Hello World from Forge!");
            }
        }
    }
}
