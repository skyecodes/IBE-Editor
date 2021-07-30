package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.guapi.Guapi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        LOGGER.info("Initializing IBE Editor - client");
        ClientConfiguration.load();
        syncGuapiConfig();
        KeyBindings.init();
    }

    public static void syncGuapiConfig() {
        Guapi.setDebugMode(ClientConfiguration.INSTANCE.getGuapiDebugMode());
        Guapi.setTheme(ClientConfiguration.INSTANCE.getGuapiTheme());
    }
}
