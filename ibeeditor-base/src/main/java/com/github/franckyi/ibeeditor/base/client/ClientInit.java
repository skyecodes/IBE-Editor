package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.base.NodeFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        LOGGER.info("Initializing IBE Editor - client");
        Guapi.setNodeFactory(NodeFactoryImpl.INSTANCE);
        Guapi.setExceptionHandler(ModGuapiExceptionHandler.INSTANCE);
    }

    public static void setup() {
        LOGGER.info("Setting up IBE Editor - client");
        ClientConfiguration.load();
        syncGuapiConfig();
        KeyBindings.init();
        ClientCache.init();
    }

    public static void syncGuapiConfig() {
        Guapi.setDebugMode(ClientConfiguration.INSTANCE.getGuapiDebugMode());
        Guapi.setTheme(ClientConfiguration.INSTANCE.getGuapiTheme());
    }
}
