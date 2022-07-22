package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.base.NodeFactoryImpl;
import com.github.franckyi.guapi.base.ScreenHandlerImpl;
import com.github.franckyi.guapi.base.theme.vanilla.VanillaTheme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        LOGGER.info("Initializing IBE Editor - client");
        Guapi.registerTheme("vanilla", VanillaTheme.INSTANCE);
        Guapi.setNodeFactory(NodeFactoryImpl.INSTANCE);
        Guapi.setScreenHandler(ScreenHandlerImpl.INSTANCE);
        Guapi.setDefaultLogger(LogManager.getLogger("IBE Editor"));
        Guapi.setExceptionHandler(ModGuapiExceptionHandler.INSTANCE);
    }

    public static void setup() {
        LOGGER.info("Setting up IBE Editor - client");
        ClientConfiguration.load();
        syncGuapiConfig();
        Vault.load();
    }

    public static void syncGuapiConfig() {
        Guapi.setDebugMode(ClientConfiguration.INSTANCE.getGuapiDebugMode());
        Guapi.setTheme(ClientConfiguration.INSTANCE.getGuapiTheme());
    }
}
