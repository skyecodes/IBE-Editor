package com.github.franckyi.gameadapter;

import com.github.franckyi.gameadapter.api.GameClient;
import com.github.franckyi.gameadapter.api.GameCommon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Game {
    private static GameCommon common;
    private static GameClient client;
    private static Logger defaultLogger = LogManager.getLogger();

    public static GameCommon getCommon() {
        return common;
    }

    public static void setCommon(GameCommon common) {
        Game.common = common;
    }

    public static GameClient getClient() {
        return client;
    }

    public static void setClient(GameClient client) {
        Game.client = client;
    }

    public static Logger getDefaultLogger() {
        return defaultLogger;
    }

    public static void setDefaultLogger(Logger defaultLogger) {
        Game.defaultLogger = defaultLogger;
    }
}
