package com.github.franckyi.ibeeditor.impl;

import com.github.franckyi.guapi.impl.theme.vanilla.*;
import com.github.franckyi.guapi.util.NodeType;
import com.github.franckyi.ibeeditor.impl.client.ClientEventHandler;
import com.github.franckyi.ibeeditor.impl.client.ClientInit;
import com.github.franckyi.ibeeditor.impl.common.CommonInit;
import com.github.franckyi.ibeeditor.impl.server.ServerCommandHandler;
import com.github.franckyi.minecraft.impl.FabricMinecraftClient;
import com.github.franckyi.minecraft.impl.FabricMinecraftCommon;
import com.github.franckyi.minecraft.impl.client.screen.FabricScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;

public final class IBEEditorFabricMod implements ModInitializer, ClientModInitializer {
    @Override
    public void onInitialize() {
        CommonInit.init(FabricMinecraftCommon.INSTANCE);
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> ServerCommandHandler.registerCommand(dispatcher));
    }

    @Override
    public void onInitializeClient() {
        ClientInit.init(FabricMinecraftClient.INSTANCE);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.BUTTON, FabricVanillaButtonRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.TEXTURED_BUTTON, FabricVanillaTexturedButtonRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.TEXT_FIELD, FabricVanillaTextFieldRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.CHECK_BOX, FabricVanillaCheckBoxRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.LIST_VIEW, FabricVanillaListViewRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.TREE_VIEW, FabricVanillaTreeViewRenderer::new);
        ScreenEvents.AFTER_INIT.register(this::afterScreenInit);
    }

    private void afterScreenInit(MinecraftClient mc, Screen screen, int width, int height) {
        if (screen instanceof HandledScreen) {
            ScreenKeyboardEvents.beforeKeyPress(screen).register(this::handledScreenKeyPressed);
        }
    }

    private void handledScreenKeyPressed(Screen screen, int key, int scancode, int modifiers) {
        ClientEventHandler.onScreenEvent(new FabricScreen(screen), key);
    }
}
