package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.ibeeditor.PlatformUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
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
    private static final Path OLD_COMMON_CONFIG_FILE = PlatformUtil.getConfigDir().resolve("ibeeditor-base.json");
    private static final Path COMMON_CONFIG_FILE = PlatformUtil.getConfigDir().resolve("ibeeditor-common.json");
    public static CommonConfiguration INSTANCE;
    private static boolean changed;

    private int version;
    private int permissionLevel;
    private boolean creativeOnly;

    private CommonConfiguration() {
        version = 0;
        permissionLevel = 0;
        creativeOnly = false;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        if (this.permissionLevel != permissionLevel) {
            this.permissionLevel = permissionLevel;
            changed = true;
        }
    }

    public boolean isCreativeOnly() {
        return creativeOnly;
    }

    public void setCreativeOnly(boolean creativeOnly) {
        if (this.creativeOnly != creativeOnly) {
            this.creativeOnly = creativeOnly;
            changed = true;
        }
    }

    public static void load() {
        if (Files.exists(COMMON_CONFIG_FILE)) {
            try (Reader r = Files.newBufferedReader(COMMON_CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(r, CommonConfiguration.class);
                LOGGER.info("Common configuration loaded");
            } catch (IOException | JsonSyntaxException e) {
                LOGGER.error("Error while loading common configuration", e);
                INSTANCE = new CommonConfiguration();
            }
        } else {
            // Config file renamed in version 2.0.8
            if (Files.exists(OLD_COMMON_CONFIG_FILE)) {
                try {
                    LOGGER.info("Old common configuration file detected; deleting it");
                    Files.deleteIfExists(OLD_COMMON_CONFIG_FILE);
                } catch (IOException e) {
                    LOGGER.error("Error while deleting old common configuration file", e);
                }
            }
            LOGGER.info("Generating default common configuration");
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
                LOGGER.info("Common configuration saved");
            } catch (IOException e) {
                LOGGER.error("Error while saving common configuration", e);
            }
        }
    }
}
