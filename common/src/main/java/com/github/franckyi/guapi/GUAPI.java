package com.github.franckyi.guapi;

import com.github.franckyi.guapi.api.theme.Theme;
import com.github.franckyi.guapi.impl.theme.vanilla.VanillaTheme;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public final class GUAPI {
    public static final Marker MARKER = MarkerManager.getMarker("GUAPI");
    private static Theme theme;
    private static boolean debugMode = false;

    public static void init() {
        setTheme(VanillaTheme.INSTANCE);
    }

    public static Theme getTheme() {
        return theme;
    }

    public static void setTheme(Theme theme) {
        GUAPI.theme = theme;
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static void setDebugMode(boolean debugMode) {
        GUAPI.debugMode = debugMode;
    }
}
