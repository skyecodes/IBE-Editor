package com.github.franckyi.guapi;

import com.github.franckyi.guapi.api.NodeFactory;
import com.github.franckyi.guapi.api.theme.Theme;
import com.github.franckyi.guapi.impl.NodeFactoryImpl;
import com.github.franckyi.guapi.impl.theme.vanilla.VanillaTheme;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.HashMap;
import java.util.Map;

public final class GUAPI {
    public static final Marker LOG_MARKER = MarkerManager.getMarker("GUAPI");
    private static final Map<String, Theme> themeRegistry = new HashMap<>();
    private static Theme theme;
    private static NodeFactory nodeFactory = NodeFactoryImpl.INSTANCE;
    private static boolean debugMode = false;

    public static void init() {
        registerTheme("vanilla", VanillaTheme.INSTANCE);
    }

    public static Theme getTheme() {
        return theme;
    }

    public static void setTheme(String theme) {
        GUAPI.theme = themeRegistry.get(theme);
    }

    public static void registerTheme(String name, Theme theme) {
        themeRegistry.put(name, theme);
    }

    public static NodeFactory getNodeFactory() {
        return nodeFactory;
    }

    public static void setNodeFactory(NodeFactory nodeFactory) {
        GUAPI.nodeFactory = nodeFactory;
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static void setDebugMode(boolean debugMode) {
        GUAPI.debugMode = debugMode;
    }
}
