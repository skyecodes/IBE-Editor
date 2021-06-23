package com.github.franckyi.guapi;

import com.github.franckyi.guapi.api.NodeFactory;
import com.github.franckyi.guapi.api.theme.Theme;
import com.github.franckyi.guapi.impl.NodeFactoryImpl;
import com.github.franckyi.guapi.impl.theme.vanilla.VanillaTheme;
import com.github.franckyi.guapi.util.DebugMode;
import com.github.franckyi.minecraft.Minecraft;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.HashMap;
import java.util.Map;

public final class GUAPI {
    public static final Marker LOG_MARKER = MarkerManager.getMarker("GUAPI");
    private static final String DEFAULT_THEME_NAME = "vanilla";
    private static final Map<String, Theme> themeRegistry = new HashMap<>();
    private static Theme theme = VanillaTheme.INSTANCE;
    private static NodeFactory nodeFactory = NodeFactoryImpl.INSTANCE;
    private static DebugMode debugMode = DebugMode.OFF;

    public static void init() {
        registerTheme(DEFAULT_THEME_NAME, VanillaTheme.INSTANCE);
    }

    public static Theme getTheme() {
        return theme;
    }

    public static void setTheme(String theme) {
        if (!themeRegistry.containsKey(theme)) {
            Minecraft.getDefaultLogger().error("\"{}\" theme isn't registered, using default \"{}\" theme", theme, DEFAULT_THEME_NAME);
            theme = DEFAULT_THEME_NAME;
        }
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

    public static DebugMode getDebugMode() {
        return debugMode;
    }

    public static void setDebugMode(DebugMode debugMode) {
        GUAPI.debugMode = debugMode;
    }
}
