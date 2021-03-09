package com.github.franckyi.minecraft;

import com.github.franckyi.minecraft.api.MinecraftClient;
import com.github.franckyi.minecraft.api.MinecraftCommon;
import org.apache.logging.log4j.Logger;

public final class Minecraft {
    private static MinecraftCommon common;
    private static MinecraftClient client;
    private static Logger logger;

    public static void setCommon(MinecraftCommon common) {
        Minecraft.common = common;
    }

    public static void setClient(MinecraftClient client) {
        Minecraft.client = client;
    }

    public static void setLogger(Logger logger) {
        Minecraft.logger = logger;
    }

    public static MinecraftCommon getCommon() {
        return common;
    }

    public static MinecraftClient getClient() {
        return client;
    }

    public static Logger getLogger() {
        return logger;
    }
}
