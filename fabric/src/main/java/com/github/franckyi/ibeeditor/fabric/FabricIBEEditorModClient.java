package com.github.franckyi.ibeeditor.fabric;

import com.github.franckyi.ibeeditor.client.ClientEventHandler;
import com.github.franckyi.ibeeditor.client.ClientInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

public class FabricIBEEditorModClient implements ClientModInitializer {
    public FabricIBEEditorModClient() {
        ClientInit.init();
    }

    @Override
    public void onInitializeClient() {
        ClientInit.setup();
        ScreenEvents.AFTER_INIT.register(this::afterScreenInit);
    }

    private void afterScreenInit(Minecraft mc, Screen screen, int width, int height) {
        if (screen instanceof AbstractContainerScreen) {
            ScreenKeyboardEvents.beforeKeyPress(screen).register(this::handledScreenKeyPressed);
        }
    }

    private void handledScreenKeyPressed(Screen screen, int key, int scancode, int modifiers) {
        ClientEventHandler.onScreenEvent((AbstractContainerScreen<?>) screen, key);
    }
}
