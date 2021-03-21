package com.github.franckyi.minecraft;

import com.github.franckyi.minecraft.api.MinecraftClient;
import com.github.franckyi.minecraft.api.MinecraftCommon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Minecraft {
    private static MinecraftCommon common;
    private static MinecraftClient client;
    private static Logger defaultLogger = LogManager.getLogger();

    public static MinecraftCommon getCommon() {
        return common;
    }

    public static void setCommon(MinecraftCommon common) {
        Minecraft.common = common;
    }

    public static MinecraftClient getClient() {
        return client;
    }

    public static void setClient(MinecraftClient client) {
        Minecraft.client = client;
    }

    public static Logger getDefaultLogger() {
        return defaultLogger;
    }

    public static void setDefaultLogger(Logger defaultLogger) {
        Minecraft.defaultLogger = defaultLogger;
    }
}
