package com.github.franckyi.guapi;

import com.github.franckyi.guapi.hooks.api.ScreenHandler;
import com.github.franckyi.guapi.theme.Theme;
import com.github.franckyi.guapi.theme.vanilla.VanillaTheme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GUAPI {
    private static Theme theme = VanillaTheme.INSTANCE;
    private static boolean debugMode = false;
    private static ScreenHandler screenHandler;
    private static boolean initialized;
    private static Logger logger;

    public static void init(ScreenHandler screenHandler) {
        if (isInitialized()) {
            throw new IllegalStateException("GUAPI is already initialized");
        }
        if (screenHandler == null) {
            throw new IllegalArgumentException("ScreenHandler can't be null");
        }
        GUAPI.screenHandler = screenHandler;
        logger = LogManager.getLogger();
        initialized = true;
        setDebugMode(true);
        logger.info("GUAPI initialized");
    }

    public static Theme getTheme() {
        return theme;
    }

    public static void setTheme(Theme theme) {
        if (theme == null) {
            throw new IllegalArgumentException("Theme can't be null");
        }
        GUAPI.theme = theme;
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static void setDebugMode(boolean debugMode) {
        GUAPI.debugMode = debugMode;
    }

    public static ScreenHandler getScreenHandler() {
        if (!isInitialized()) {
            throw new IllegalStateException("GUAPI is not initialized");
        }
        return screenHandler;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static Logger getLogger() {
        return logger;
    }
}
