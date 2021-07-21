package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.guapi.Guapi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        LOGGER.debug("Initializing IBE Editor - client");
        ClientConfiguration.load();
        syncGuapiConfig();
        KeyBindings.init();
    }

    public static void syncGuapiConfig() {
        Guapi.setDebugMode(ClientConfiguration.INSTANCE.getGuapiDebugMode());
        Guapi.setTheme(ClientConfiguration.INSTANCE.getGuapiTheme());
    }
}
