package com.github.franckyi.guapi;

import com.github.franckyi.guapi.api.NodeFactory;
import com.github.franckyi.guapi.api.theme.Theme;
import com.github.franckyi.guapi.util.DebugMode;
import com.github.franckyi.minecraft.Minecraft;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class GUAPI {
    public static final Marker LOG_MARKER = MarkerManager.getMarker("GUAPI");
    private static final Map<String, Theme> themeRegistry = new HashMap<>();
    private static Theme theme;
    private static NodeFactory nodeFactory;
    private static DebugMode debugMode = DebugMode.OFF;

    public static Theme getTheme() {
        return theme;
    }

    public static void setTheme(String theme) {
        if (!themeRegistry.containsKey(theme)) {
            Optional<String> defaultTheme = themeRegistry.keySet().stream().findFirst();
            if (defaultTheme.isPresent()) {
                String defaultThemeName = defaultTheme.get();
                Minecraft.getDefaultLogger().error("\"{}\" theme isn't registered, using default \"{}\" theme", theme, defaultThemeName);
                theme = defaultThemeName;
            } else {
                Minecraft.getDefaultLogger().error("\"{}\" theme isn't registered, and no other theme is registered!", theme);
            }
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
