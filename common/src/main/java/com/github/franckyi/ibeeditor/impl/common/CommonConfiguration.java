package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.minecraft.Minecraft;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class CommonConfiguration {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path COMMON_CONFIG_FILE = Minecraft.getCommon().getGameDir().resolve("config").resolve("ibeeditor-common.json");
    public static CommonConfiguration INSTANCE;
    private static boolean changed;

    public static void load() {
        if (Files.exists(COMMON_CONFIG_FILE)) {
            try (Reader r = Files.newBufferedReader(COMMON_CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(r, CommonConfiguration.class);
                LOGGER.debug("Common configuration loaded");
            } catch (IOException e) {
                LOGGER.error("Error while loading common configuration", e);
            }
        } else {
            LOGGER.debug("Generating default common configuration");
            INSTANCE = new CommonConfiguration();
            changed = true;
            save();
        }
    }

    public static void save() {
        if (changed) {
            try (Writer w = Files.newBufferedWriter(COMMON_CONFIG_FILE)) {
                GSON.toJson(INSTANCE, w);
                changed = false;
                LOGGER.debug("Common configuration saved");
            } catch (IOException e) {
                LOGGER.error("Error while saving common configuration", e);
            }
        }
    }
}
