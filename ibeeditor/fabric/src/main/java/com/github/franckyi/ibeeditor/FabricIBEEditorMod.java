package com.github.franckyi.ibeeditor;

import com.github.franckyi.gameadapter.api.client.IScreen;
import com.github.franckyi.ibeeditor.base.client.ClientEventHandler;
import com.github.franckyi.ibeeditor.base.client.ClientInit;
import com.github.franckyi.ibeeditor.base.client.util.ScreenScalingManager;
import com.github.franckyi.ibeeditor.base.common.CommonInit;
import com.github.franckyi.ibeeditor.base.common.ModNetwork;
import com.github.franckyi.ibeeditor.base.server.ServerCommandHandler;
import com.github.franckyi.ibeeditor.fabric.client.util.FabricScreenScalingManager;
import com.github.franckyi.ibeeditor.fabric.common.FabricNetwork;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;

public final class FabricIBEEditorMod implements ModInitializer, ClientModInitializer {
    @Override
    public void onInitialize() {
        ModNetwork.init(FabricNetwork.INSTANCE);
        CommonInit.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> ServerCommandHandler.registerCommand(dispatcher));
    }

    @Override
    public void onInitializeClient() {
        ClientInit.init();
        ScreenScalingManager.init(FabricScreenScalingManager.INSTANCE);
        ScreenEvents.AFTER_INIT.register(this::afterScreenInit);
    }

    private void afterScreenInit(MinecraftClient mc, Screen screen, int width, int height) {
        if (screen instanceof HandledScreen) {
            ScreenKeyboardEvents.beforeKeyPress(screen).register(this::handledScreenKeyPressed);
        }
    }

    private void handledScreenKeyPressed(Screen screen, int key, int scancode, int modifiers) {
        ClientEventHandler.onScreenEvent((IScreen) screen, key);
    }
}
