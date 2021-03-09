package com.github.franckyi.ibeeditor.impl;

import com.github.franckyi.guapi.impl.theme.vanilla.*;
import com.github.franckyi.guapi.util.NodeType;
import com.github.franckyi.ibeeditor.impl.client.IBEEditorClient;
import com.github.franckyi.ibeeditor.impl.common.IBEEditorCommon;
import com.github.franckyi.ibeeditor.impl.server.IBEEditorServer;
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
        IBEEditorCommon.init(ForgeMinecraftCommon.INSTANCE);
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedIn);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedOut);
    }

    private void onClientInit(FMLClientSetupEvent event) {
        IBEEditorClient.init(ForgeMinecraftClient.INSTANCE);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.BUTTON, ForgeVanillaButtonRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.TEXTURED_BUTTON, ForgeVanillaTexturedButtonRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.TEXT_FIELD, ForgeVanillaTextFieldRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.CHECK_BOX, ForgeVanillaCheckBoxRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.LIST_VIEW, ForgeVanillaListViewRenderer::new);
        VanillaTheme.INSTANCE.registerDelegatedSkinRenderer(NodeType.TREE_VIEW, ForgeVanillaTreeViewRenderer::new);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyInput);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyPressed);
        MinecraftForge.EVENT_BUS.addListener(this::onWorldUnload);
    }

    private void onKeyInput(InputEvent.KeyInputEvent e) {
        if (Minecraft.getInstance().currentScreen == null) {
            IBEEditorClient.onKeyInput();
        }
    }

    private void onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre e) {
        if (e.getGui() instanceof ContainerScreen) {
            IBEEditorClient.handleScreenEvent(new ForgeScreen(e.getGui()), e.getKeyCode());
        }
    }

    private void onServerStarting(FMLServerStartingEvent event) {
        IBEEditorServer.registerCommand(event.getServer().getCommandManager().getDispatcher());
    }

    private void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        IBEEditorServer.notifyClient(new ForgePlayer(event.getPlayer()));
    }

    private void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        IBEEditorServer.removeAllowedPlayer(new ForgePlayer(event.getPlayer()));
    }

    private void onWorldUnload(WorldEvent.Unload event) {
        IBEEditorClient.setServerModInstalled(false);
    }
}
