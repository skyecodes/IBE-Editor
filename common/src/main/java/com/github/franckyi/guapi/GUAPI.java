package com.github.franckyi.guapi;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.hooks.api.ScreenHandler;
import com.github.franckyi.guapi.theme.Theme;
import com.github.franckyi.guapi.theme.vanilla.VanillaTheme;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public final class GUAPI {
    private static Theme theme = VanillaTheme.INSTANCE;
    private static boolean debugMode = false;
    private static ScreenHandler screenHandler;
    public static final Marker MARKER = MarkerManager.getMarker("GUAPI");

    public static void init(ScreenHandler screenHandler) {
        if (GUAPI.screenHandler != null) {
            throw new IllegalStateException("GUAPI is already initialized");
        }
        if (screenHandler == null) {
            throw new IllegalArgumentException("ScreenHandler can't be null");
        }
        GUAPI.screenHandler = screenHandler;
        GameHooks.getLogger().info(MARKER, "GUAPI initialized");
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
        if (screenHandler == null) {
            throw new IllegalStateException("GUAPI is not initialized");
        }
        return screenHandler;
    }
}
