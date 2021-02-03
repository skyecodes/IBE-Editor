package com.github.franckyi.gamehooks;

import com.github.franckyi.gamehooks.api.Hooks;
import com.github.franckyi.gamehooks.api.Renderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GameHooks {
    private static Hooks hooks;
    private static boolean initialized;
    private static Logger logger;

    public static void init(Hooks hooks) {
        if (isInitialized()) {
            throw new RuntimeException("GameHooks is already initialized");
        }
        if (hooks == null) {
            throw new IllegalArgumentException("Hooks can't be null");
        }
        GameHooks.hooks = hooks;
        logger = LogManager.getLogger();
        initialized = true;
        logger.info("GameHooks initialized");
    }

    public static <MS> Renderer<MS> getRenderer() {
        checkIsInitialized();
        return hooks.renderer();
    }

    private static void checkIsInitialized() {
        if (!isInitialized()) {
            throw new IllegalStateException("GameHooks is not initialized");
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static Logger getLogger() {
        return logger;
    }
}
