package com.github.franckyi.ibeeditor.impl;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.theme.DelegatedRendererProvider;
import com.github.franckyi.guapi.impl.theme.vanilla.*;
import com.github.franckyi.guapi.util.NodeType;
import com.github.franckyi.ibeeditor.impl.client.ClientContext;
import com.github.franckyi.ibeeditor.impl.client.ClientEventHandler;
import com.github.franckyi.ibeeditor.impl.client.ClientInit;
import com.github.franckyi.ibeeditor.impl.common.CommonInit;
import com.github.franckyi.ibeeditor.impl.server.ServerCommandHandler;
import com.github.franckyi.ibeeditor.impl.server.ServerEventHandler;
import com.github.franckyi.minecraft.impl.ForgeMinecraftClient;
import com.github.franckyi.minecraft.impl.ForgeMinecraftCommon;
import com.github.franckyi.minecraft.impl.client.screen.ForgeScreen;
import com.github.franckyi.minecraft.impl.common.world.ForgePlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("ibeeditor")
public final class IBEEditorForgeMod {
    public IBEEditorForgeMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientInit);
    }

    private void onCommonInit(FMLCommonSetupEvent event) {
        CommonInit.init(ForgeMinecraftCommon.INSTANCE);
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedIn);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedOut);
    }

    private void onClientInit(FMLClientSetupEvent event) {
        ClientInit.init(ForgeMinecraftClient.INSTANCE);
        initSkin(NodeType.BUTTON, ForgeVanillaButtonRenderer::new);
        initSkin(NodeType.TEXTURED_BUTTON, ForgeVanillaTexturedButtonRenderer::new);
        initSkin(NodeType.ENUM_BUTTON, ForgeVanillaButtonRenderer::new);
        initSkin(NodeType.TEXT_FIELD, ForgeVanillaTextFieldRenderer::new);
        initSkin(NodeType.CHECK_BOX, ForgeVanillaCheckBoxRenderer::new);
        initSkin(NodeType.LIST_VIEW, ForgeVanillaListViewRenderer::new);
        initSkin(NodeType.TREE_VIEW, ForgeVanillaTreeViewRenderer::new);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyInput);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyPressed);
        MinecraftForge.EVENT_BUS.addListener(this::onWorldUnload);
    }

    private <N extends Node> void initSkin(NodeType<N> type, DelegatedRendererProvider<N> delegatedRendererProvider) {
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(type, delegatedRendererProvider);
    }

    private void onKeyInput(InputEvent.KeyInputEvent e) {
        if (Minecraft.getInstance().screen == null) {
            ClientEventHandler.onKeyInput();
        }
    }

    private void onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre e) {
        if (e.getGui() instanceof ContainerScreen) {
            ClientEventHandler.onScreenEvent(new ForgeScreen(e.getGui()), e.getKeyCode());
        }
    }

    private void onServerStarting(FMLServerStartingEvent event) {
        ServerCommandHandler.registerCommand(event.getServer().getCommands().getDispatcher());
    }

    private void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ServerEventHandler.onPlayerJoin(new ForgePlayer(event.getPlayer()));
    }

    private void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        ServerEventHandler.onPlayerLeave(new ForgePlayer(event.getPlayer()));
    }

    private void onWorldUnload(WorldEvent.Unload event) {
        ClientContext.setModInstalledOnServer(false);
    }
}
