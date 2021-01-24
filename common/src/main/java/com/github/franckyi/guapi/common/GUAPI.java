package com.github.franckyi.guapi.common;

import com.github.franckyi.guapi.common.hooks.Renderer;
import com.github.franckyi.guapi.common.hooks.ScreenHandler;
import com.github.franckyi.guapi.common.skin.Theme;
import com.github.franckyi.guapi.common.theme.vanilla.VanillaTheme;

public final class GUAPI {
    private static Theme theme = VanillaTheme.INSTANCE;
    private static Renderer<?> renderer;
    private static ScreenHandler screenHandler;
    private static boolean initialized;

    public static void setTheme(Theme theme) {
        if (theme == null) {
            throw new IllegalArgumentException("Theme can't be null");
        }
        GUAPI.theme = theme;
    }

    public static void init(Renderer<?> renderer, ScreenHandler screenHandler) {
        if (initialized) {
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
    }

    public static Theme getTheme() {
        return theme;
    }

    public static <T> Renderer<T> getRenderer() {
        return (Renderer<T>) renderer;
    }

    public static ScreenHandler getScreenHandler() {
        return screenHandler;
    }

    public static boolean isInitialized() {
        return initialized;
    }
}
