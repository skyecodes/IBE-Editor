package com.github.franckyi.ibeeditor;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.impl.ForgeClientHooks;
import com.github.franckyi.gamehooks.impl.ForgeCommonHooks;
import com.github.franckyi.gamehooks.impl.ForgeServerHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.hooks.impl.ForgeScreenHandler;
import com.github.franckyi.guapi.hooks.impl.theme.vanilla.ForgeVanillaThemeRenderer;
import com.github.franckyi.guapi.theme.vanilla.VanillaTheme;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

@Mod("ibeeditor")
public final class IBEEditorForgeMod {
    private final Logger logger = IBEEditor.LOGGER;
    private final Marker marker = MarkerManager.getMarker("ModInit");

    public IBEEditorForgeMod() {
        logger.info(marker, "Constructing mod");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onServerInit);
    }

    private void onCommonInit(FMLCommonSetupEvent event) {
        logger.info(marker, "Initializing mod - common");
        GameHooks.initCommon(ForgeCommonHooks.INSTANCE, logger);
    }

    private void onClientInit(FMLClientSetupEvent event) {
        logger.info(marker, "Initializing mod - client");
        GameHooks.initClient(ForgeClientHooks.INSTANCE);
        GUAPI.init(ForgeScreenHandler.INSTANCE);
        GUAPI.setTheme(VanillaTheme.create(ForgeVanillaThemeRenderer.INSTANCE));
        IBEEditorClient.init();
        MinecraftForge.EVENT_BUS.addListener(this::onClientTick);
    }

    private void onServerInit(FMLDedicatedServerSetupEvent event) {
        logger.info(marker, "Initializing mod - server");
        GameHooks.initServer(ForgeServerHooks.INSTANCE);
    }

    private void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            IBEEditorClient.tick();
        }
    }
}
