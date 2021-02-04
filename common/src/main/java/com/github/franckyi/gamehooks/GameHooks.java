package com.github.franckyi.gamehooks;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.ServerHooks;
import com.github.franckyi.gamehooks.api.client.KeyBindings;
import com.github.franckyi.gamehooks.api.client.Renderer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public final class GameHooks {
    private static CommonHooks commonHooks;
    private static ClientHooks clientHooks;
    private static ServerHooks serverHooks;
    private static Logger logger;
    private static final Marker MARKER = MarkerManager.getMarker("GameHooks");

    public static void initCommon(CommonHooks commonHooks, Logger logger) {
        if (GameHooks.commonHooks != null) {
            throw new IllegalStateException("CommonHooks is already initialized");
        }
        if (commonHooks == null) {
            throw new IllegalArgumentException("CommonHooks can't be null");
        }
        GameHooks.commonHooks = commonHooks;
        GameHooks.logger = logger;
        logger.info(MARKER, "CommonHooks initialized");
    }

    public static void initClient(ClientHooks clientHooks) {
        if (GameHooks.clientHooks != null) {
            throw new IllegalStateException("ClientHooks is already initialized");
        }
        if (clientHooks == null) {
            throw new IllegalArgumentException("ClientHooks can't be null");
        }
        GameHooks.clientHooks = clientHooks;
        logger.info(MARKER, "ClientHooks initialized");
    }

    public static void initServer(ServerHooks serverHooks) {
        if (GameHooks.serverHooks != null) {
            throw new IllegalStateException("ServerHooks is already initialized");
        }
        if (serverHooks == null) {
            throw new IllegalArgumentException("ServerHooks can't be null");
        }
        GameHooks.serverHooks = serverHooks;
        logger.info(MARKER, "ServerHooks initialized");
    }

    public static <MS> Renderer<MS> renderer() {
        checkIsClientInitialized();
        return clientHooks.renderer();
    }

    public static KeyBindings keyBindings() {
        checkIsClientInitialized();
        return clientHooks.keyBindings();
    }

    public static Logger getLogger() {
        return logger;
    }

    private static void checkIsCommonInitialized() {
        if (commonHooks == null) {
            throw new IllegalStateException("CommonHooks is not initialized");
        }
    }

    private static void checkIsClientInitialized() {
        if (clientHooks == null) {
            throw new IllegalStateException("ClientHooks is not initialized");
        }
    }

    private static void checkIsServerInitialized() {
        if (serverHooks == null) {
            throw new IllegalStateException("ServerHooks is not initialized");
        }
    }
}
