package com.github.franckyi.ibeeditor.forge;

import com.github.franckyi.ibeeditor.base.client.ClientContext;
import com.github.franckyi.ibeeditor.base.client.ClientEventHandler;
import com.github.franckyi.ibeeditor.base.client.ClientInit;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.common.CommonInit;
import com.github.franckyi.ibeeditor.base.server.ServerCommandHandler;
import com.github.franckyi.ibeeditor.base.server.ServerEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;

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
        CommonInit.setup();
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedIn);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedOut);
    }

    private void onClientInit(FMLClientSetupEvent event) {
        ClientInit.setup();
        MinecraftForge.EVENT_BUS.addListener(this::onKeyInput);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyPressed);
        MinecraftForge.EVENT_BUS.addListener(this::onWorldUnload);
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (minecraft, screen) -> {
            ModScreenHandler.openSettingsScreen();
            return minecraft.screen;
        });
        //event.enqueueWork(ForgeIBEEditorClientInit::optimize);
    }

    private void onServerStarting(FMLServerStartingEvent event) {
        ServerCommandHandler.registerCommand(event.getServer().getCommands().getDispatcher());
    }

    private void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ServerEventHandler.onPlayerJoin((ServerPlayer) event.getPlayer());
    }

    private void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        ServerEventHandler.onPlayerLeave((ServerPlayer) event.getPlayer());
    }

    private void onKeyInput(InputEvent.KeyInputEvent e) {
        if (Minecraft.getInstance().screen == null) {
            ClientEventHandler.onKeyInput();
        }
    }

    private void onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre e) {
        if (e.getGui() instanceof AbstractContainerScreen) {
            e.setCanceled(ClientEventHandler.onScreenEvent(((AbstractContainerScreen<?>) e.getGui()), e.getKeyCode()));
        }
    }

    private void onWorldUnload(WorldEvent.Unload event) {
        ClientContext.setModInstalledOnServer(false);
    }
}
