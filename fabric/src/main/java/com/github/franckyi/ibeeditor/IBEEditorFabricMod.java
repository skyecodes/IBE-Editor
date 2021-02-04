package com.github.franckyi.ibeeditor;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.impl.FabricClientHooks;
import com.github.franckyi.gamehooks.impl.FabricCommonHooks;
import com.github.franckyi.gamehooks.impl.FabricServerHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.hooks.impl.FabricScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public final class IBEEditorFabricMod implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {
    private final Logger logger = IBEEditor.LOGGER;
    private final Marker marker = MarkerManager.getMarker("ModInit");

    @Override
    public void onInitialize() {
        logger.info(marker, "Initializing mod - common");
        GameHooks.initCommon(FabricCommonHooks.INSTANCE, logger);
    }

    @Override
    public void onInitializeClient() {
        logger.info(marker, "Initializing mod - client");
        GameHooks.initClient(FabricClientHooks.INSTANCE);
        GUAPI.init(FabricScreenHandler.INSTANCE);
        IBEEditorClient.init();
        ClientTickEvents.END_CLIENT_TICK.register(mc -> IBEEditorClient.tick());
    }

    @Override
    public void onInitializeServer() {
        logger.info(marker, "Initializing mod - server");
        GameHooks.initServer(FabricServerHooks.INSTANCE);
    }
}
