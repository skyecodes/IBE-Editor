package com.github.franckyi.ibeeditor;

import com.github.franckyi.gameadapter.api.client.IMatrices;
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
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.util.ResourceLocation;
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
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

@Mod("ibeeditor")
public final class ForgeIBEEditorMod {
    private static final Logger LOGGER = LogManager.getLogger();

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
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (minecraft, screen) -> {
            ModScreenHandler.openSettingsScreen();
            return minecraft.screen;
        });
        event.enqueueWork(() -> {
            ClientInit.setup();
            StopWatch watch = StopWatch.createStarted();
            Arrays.stream(ModTextures.class.getDeclaredFields()).map(field -> {
                try {
                    return (ResourceLocation) field.get(null);
                } catch (IllegalAccessException e) {
                    LOGGER.error(e);
                    return null;
                }
            }).forEach(id -> Minecraft.getInstance().getTextureManager().register(id, new SimpleTexture(id)));
            ModScreenHandler.optimize((IMatrices) new MatrixStack());
            watch.stop();
            LOGGER.info("Optimized IBE Editor (took {} s)", watch.getTime() / 1000.);
        });
    }

    private void onKeyInput(InputEvent.KeyInputEvent e) {
        if (Minecraft.getInstance().screen == null) {
            ClientEventHandler.onKeyInput();
        }
    }

    private void onKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent.Pre e) {
        if (e.getGui() instanceof ContainerScreen) {
            ClientEventHandler.onScreenEvent((IScreen) e.getGui(), e.getKeyCode());
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
}
