package com.github.franckyi.ibeeditor.forge;

import com.github.franckyi.gameadapter.api.client.IScreen;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.ibeeditor.base.client.*;
import com.github.franckyi.ibeeditor.base.client.util.ScreenScalingManager;
import com.github.franckyi.ibeeditor.base.common.CommonInit;
import com.github.franckyi.ibeeditor.base.common.NetworkManager;
import com.github.franckyi.ibeeditor.base.server.ServerCommandHandler;
import com.github.franckyi.ibeeditor.base.server.ServerEventHandler;
import com.github.franckyi.ibeeditor.forge.client.util.ForgeScreenScalingManager;
import com.github.franckyi.ibeeditor.forge.common.ForgeNetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fmlclient.ConfigGuiHandler;
import net.minecraftforge.fmllegacy.event.lifecycle.FMLModIdMappingEvent;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;

@Mod("ibeeditor")
public final class ForgeIBEEditorMod {
    public ForgeIBEEditorMod() {
        CommonInit.init();
        if (FMLLoader.getDist() == Dist.CLIENT) {
            ClientInit.init();
        }
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientInit);
    }

    private void onCommonInit(FMLCommonSetupEvent event) {
        NetworkManager.setup(ForgeNetworkManager.INSTANCE);
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedIn);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedOut);
        event.enqueueWork(CommonInit::setup);
    }

    private void onClientInit(FMLClientSetupEvent event) {
        ScreenScalingManager.init(ForgeScreenScalingManager.INSTANCE);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyInput);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyPressed);
        MinecraftForge.EVENT_BUS.addListener(this::onWorldUnload);
        MinecraftForge.EVENT_BUS.addListener(this::initCache);
        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory((minecraft, screen) -> {
            ModScreenHandler.openSettingsScreen();
            return minecraft.screen;
        }));
        event.enqueueWork(ForgeIBEEditorClientInit::setup);
    }

    private void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Minecraft.getInstance().screen == null) {
            ClientEventHandler.onKeyInput();
        }
    }

    private void onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre event) {
        if (event.getGui() instanceof AbstractContainerScreen) {
            ClientEventHandler.onScreenEvent((IScreen) event.getGui(), event.getKeyCode());
        }
    }

    private void onServerStarting(FMLServerStartingEvent event) {
        ServerCommandHandler.registerCommand(event.getServer().getCommands().getDispatcher());
    }

    private void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ServerEventHandler.onPlayerJoin((IPlayer) event.getPlayer());
    }

    private void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        ServerEventHandler.onPlayerLeave((IPlayer) event.getPlayer());
    }

    private void onWorldUnload(WorldEvent.Unload event) {
        ClientContext.setModInstalledOnServer(false);
    }

    private void initCache(FMLModIdMappingEvent event) {
        ClientCache.init();
    }
}
