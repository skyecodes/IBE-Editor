package com.github.franckyi.gamehooks;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.CommonHooks;
import org.apache.logging.log4j.Logger;

public final class GameHooks {
    private static CommonHooks commonHooks;
    private static ClientHooks clientHooks;
    private static Logger logger;

    public static void initCommon(CommonHooks commonHooks, Logger logger) {
        if (GameHooks.commonHooks != null) {
            throw new IllegalStateException("CommonHooks is already initialized");
        }
        if (commonHooks == null) {
            throw new IllegalArgumentException("CommonHooks can't be null");
        }
        GameHooks.commonHooks = commonHooks;
        GameHooks.logger = logger;
    }

    public static void initClient(ClientHooks clientHooks) {
        if (GameHooks.clientHooks != null) {
            throw new IllegalStateException("ClientHooks is already initialized");
        }
        if (clientHooks == null) {
            throw new IllegalArgumentException("ClientHooks can't be null");
        }
        GameHooks.clientHooks = clientHooks;
    }

    public static CommonHooks common() {
        if (commonHooks == null) {
            throw new IllegalStateException("CommonHooks is not initialized");
        }
        return commonHooks;
    }

    public static ClientHooks client() {
        if (clientHooks == null) {
            throw new IllegalStateException("ClientHooks is not initialized");
        }
        return clientHooks;
    }

    public static Logger logger() {
        return logger;
    }
}
