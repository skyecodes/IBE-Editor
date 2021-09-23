package com.github.franckyi.guapi.api;

import com.github.franckyi.guapi.api.theme.Theme;
import com.github.franckyi.guapi.api.util.DebugMode;
import com.github.franckyi.guapi.base.GuapiScreenHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Guapi {
    public static final Marker LOG_MARKER = MarkerManager.getMarker("GUAPI");
    private static final Map<String, Theme> themeRegistry = new HashMap<>();
    private static Theme theme;
    private static NodeFactory nodeFactory;
    private static GuapiScreenHandler screenHandler;
    private static DebugMode debugMode = DebugMode.OFF;
    private static GuapiExceptionHandler exceptionHandler = GuapiExceptionHandler.NONE;
    private static Logger logger;

    public static Theme getTheme() {
        return theme;
    }

    public static void setTheme(String theme) {
        if (!themeRegistry.containsKey(theme)) {
            Optional<String> defaultTheme = themeRegistry.keySet().stream().findFirst();
            if (defaultTheme.isPresent()) {
                String defaultThemeName = defaultTheme.get();
                Guapi.getDefaultLogger().error(LOG_MARKER, "\"{}\" theme isn't registered, using default \"{}\" theme", theme, defaultThemeName);
                theme = defaultThemeName;
            } else {
                Guapi.getDefaultLogger().error(LOG_MARKER, "\"{}\" theme isn't registered, and no other theme is registered!", theme);
            }
        }
        Guapi.theme = themeRegistry.get(theme);
    }

    public static void registerTheme(String name, Theme theme) {
        themeRegistry.put(name, theme);
    }

    public static NodeFactory getNodeFactory() {
        return nodeFactory;
    }

    public static void setNodeFactory(NodeFactory nodeFactory) {
        Guapi.nodeFactory = nodeFactory;
    }

    public static GuapiScreenHandler getScreenHandler() {
        return screenHandler;
    }

    public static DebugMode getDebugMode() {
        return debugMode;
    }

    public static void setDebugMode(DebugMode debugMode) {
        Guapi.debugMode = debugMode;
    }

    public static void setExceptionHandler(GuapiExceptionHandler exceptionHandler) {
        Guapi.exceptionHandler = exceptionHandler;
    }

    public static GuapiExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public static void setDefaultLogger(Logger logger) {
        Guapi.logger = logger;
    }

    public static Logger getDefaultLogger() {
        return logger;
    }
}
