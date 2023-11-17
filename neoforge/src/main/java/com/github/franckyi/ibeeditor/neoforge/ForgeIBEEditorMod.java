package com.github.franckyi.ibeeditor.neoforge;

import com.github.franckyi.ibeeditor.client.*;
import com.github.franckyi.ibeeditor.common.CommonInit;
import com.github.franckyi.ibeeditor.common.ServerCommandHandler;
import com.github.franckyi.ibeeditor.common.ServerEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod("ibeeditor")
public final class ForgeIBEEditorMod {
    public ForgeIBEEditorMod() {
        CommonInit.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonInit);
        if (FMLLoader.getDist() == Dist.CLIENT) {
            ClientInit.init();
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientInit);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onRegisterKeybindings);
        }
    }

    private void onRegisterKeybindings(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.getEditorKey());
        event.register(KeyBindings.getNBTEditorKey());
        event.register(KeyBindings.getSNBTEditorKey());
        event.register(KeyBindings.getVaultKey());
    }

    private void onCommonInit(FMLCommonSetupEvent event) {
        CommonInit.setup();
        NeoForge.EVENT_BUS.addListener(this::onServerStarting);
        NeoForge.EVENT_BUS.addListener(this::onPlayerLoggedIn);
        NeoForge.EVENT_BUS.addListener(this::onPlayerLoggedOut);
    }

    private void onClientInit(FMLClientSetupEvent event) {
        ClientInit.setup();
        NeoForge.EVENT_BUS.addListener(this::onKeyInput);
        NeoForge.EVENT_BUS.addListener(this::onKeyPressed);
        NeoForge.EVENT_BUS.addListener(this::onPlayerLoggingIn);
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> {
            ModScreenHandler.openSettingsScreen();
            return minecraft.screen;
        }));
    }

    private void onServerStarting(ServerStartingEvent event) {
        ServerCommandHandler.registerCommand(event.getServer().getCommands().getDispatcher());
    }

    private void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ServerEventHandler.onPlayerJoin((ServerPlayer) event.getEntity());
    }

    private void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        ServerEventHandler.onPlayerLeave((ServerPlayer) event.getEntity());
    }

    private void onKeyInput(InputEvent.Key e) {
        if (Minecraft.getInstance().screen == null) {
            ClientEventHandler.onKeyInput();
        }
    }

    private void onKeyPressed(ScreenEvent.KeyPressed.Pre e) {
        if (e.getScreen() instanceof AbstractContainerScreen<?> screen) {
            e.setCanceled(ClientEventHandler.onScreenEvent(screen, e.getKeyCode()));
        }
    }

    private void onPlayerLoggingIn(ClientPlayerNetworkEvent.LoggingIn event) {
        ClientContext.setModInstalledOnServer(false);
    }
}
