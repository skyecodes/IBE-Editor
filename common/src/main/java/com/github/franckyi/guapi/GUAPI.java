package com.github.franckyi.guapi;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.hooks.api.ScreenHandler;
import com.github.franckyi.guapi.theme.Theme;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public final class GUAPI {
    private static final ObjectProperty<Theme> themeProperty = PropertyFactory.ofObject();
    private static boolean debugMode = false;
    private static ScreenHandler screenHandler;
    public static final Marker MARKER = MarkerManager.getMarker("GUAPI");

    public static void init(ScreenHandler screenHandler) {
        init(screenHandler, null);
    }

    public static void init(ScreenHandler screenHandler, Theme theme) {
        if (GUAPI.screenHandler != null) {
            throw new IllegalStateException("GUAPI is already initialized");
        }
        if (screenHandler == null) {
            throw new IllegalArgumentException("ScreenHandler can't be null");
        }
        GUAPI.screenHandler = screenHandler;
        if (theme != null) {
            setTheme(theme);
        }
        setDebugMode(true);
        GameHooks.getLogger().info(MARKER, "GUAPI initialized");
    }

    public static Theme getTheme() {
        return themeProperty().getValue();
    }

    public static ObjectProperty<Theme> themeProperty() {
        return themeProperty;
    }

    public static void setTheme(Theme theme) {
        themeProperty().setValue(theme);
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
