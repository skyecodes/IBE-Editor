package com.github.franckyi.ibeeditor.impl;

import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.DelegatedRendererProvider;
import com.github.franckyi.guapi.impl.theme.vanilla.*;
import com.github.franckyi.guapi.util.NodeType;
import com.github.franckyi.ibeeditor.impl.client.ClientEventHandler;
import com.github.franckyi.ibeeditor.impl.client.ClientInit;
import com.github.franckyi.ibeeditor.impl.common.CommonInit;
import com.github.franckyi.ibeeditor.impl.server.ServerCommandHandler;
import com.github.franckyi.minecraft.impl.FabricMinecraftClient;
import com.github.franckyi.minecraft.impl.FabricMinecraftCommon;
import com.github.franckyi.minecraft.impl.client.screen.FabricScreen;
import com.github.franckyi.minecraft.impl.client.screen.FabricScreenHandler;
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
        GUAPI.setScreenHandler(FabricScreenHandler.INSTANCE);
        initSkin(NodeType.BUTTON, FabricVanillaButtonRenderer::new);
        initSkin(NodeType.TEXTURED_BUTTON, FabricVanillaTexturedButtonRenderer::new);
        initSkin(NodeType.ENUM_BUTTON, FabricVanillaButtonRenderer::new);
        initSkin(NodeType.TEXT_FIELD, FabricVanillaTextFieldRenderer::new);
        initSkin(NodeType.CHECK_BOX, FabricVanillaCheckBoxRenderer::new);
        initSkin(NodeType.LIST_VIEW, FabricVanillaListViewRenderer::new);
        initSkin(NodeType.TREE_VIEW, FabricVanillaTreeViewRenderer::new);
        initSkin(NodeType.TOGGLE_BUTTON, FabricVanillaButtonRenderer::new);
        initSkin(NodeType.TEXTURED_TOGGLE_BUTTON, FabricVanillaTexturedButtonRenderer::new);
        ScreenEvents.AFTER_INIT.register(this::afterScreenInit);
    }

    private <N extends Node> void initSkin(NodeType<N> type, DelegatedRendererProvider<N> delegatedRendererProvider) {
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(type, delegatedRendererProvider);
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
