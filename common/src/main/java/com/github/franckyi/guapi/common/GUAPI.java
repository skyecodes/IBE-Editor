package com.github.franckyi.guapi.common;

import com.github.franckyi.guapi.common.hooks.Renderer;
import com.github.franckyi.guapi.common.hooks.ScreenHandler;
import com.github.franckyi.guapi.common.theme.Theme;
import com.github.franckyi.guapi.common.theme.vanilla.VanillaTheme;

public final class GUAPI {
    private static Theme theme = VanillaTheme.INSTANCE;
    private static boolean debugMode = false;
    private static Renderer<?> renderer;
    private static ScreenHandler screenHandler;
    private static boolean initialized;

    public static void init(Renderer<?> renderer, ScreenHandler screenHandler) {
        if (isInitialized()) {
            throw new RuntimeException("GUAPI is already initialized");
        }
        if (renderer == null) {
            throw new IllegalArgumentException("Renderer can't be null");
        }
        if (screenHandler == null) {
            throw new IllegalArgumentException("ScreenHandler can't be null");
        }
        GUAPI.renderer = renderer;
        GUAPI.screenHandler = screenHandler;
        initialized = true;
        setDebugMode(true);
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

    public static Renderer<?> getRenderer() {
        if (!isInitialized()) {
            throw new RuntimeException("GUAPI is not initialized");
        }
        return renderer;
    }

    public static ScreenHandler getScreenHandler() {
        if (!isInitialized()) {
            throw new RuntimeException("GUAPI is not initialized");
        }
        return screenHandler;
    }

    public static boolean isInitialized() {
        return initialized;
    }
}
