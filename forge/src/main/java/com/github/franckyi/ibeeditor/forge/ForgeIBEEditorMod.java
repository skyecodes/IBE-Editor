package com.github.franckyi.ibeeditor.forge;

import com.github.franckyi.ibeeditor.client.*;
import com.github.franckyi.ibeeditor.common.CommonInit;
import com.github.franckyi.ibeeditor.common.ServerCommandHandler;
import com.github.franckyi.ibeeditor.common.ServerEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;

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
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedIn);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedOut);
    }

    private void onClientInit(FMLClientSetupEvent event) {
        ClientInit.setup();
        MinecraftForge.EVENT_BUS.addListener(this::onKeyInput);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyPressed);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggingIn);
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
