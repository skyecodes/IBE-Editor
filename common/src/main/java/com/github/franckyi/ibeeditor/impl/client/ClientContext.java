package com.github.franckyi.ibeeditor.impl.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientContext {
    private static final Logger LOGGER = LogManager.getLogger();
    private static boolean modInstalledOnServer;

    public static boolean isModInstalledOnServer() {
        return modInstalledOnServer;
    }

    public static void setModInstalledOnServer(boolean value) {
        LOGGER.debug("Setting 'modInstalledOnServer' to {}", value);
        modInstalledOnServer = value;
    }
}
