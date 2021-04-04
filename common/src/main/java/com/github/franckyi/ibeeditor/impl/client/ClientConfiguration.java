package com.github.franckyi.ibeeditor.impl.client;

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

public final class ClientConfiguration {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CLIENT_CONFIG_FILE = Minecraft.getCommon().getGameDir().resolve("config").resolve("ibeeditor-client.json");
    public static ClientConfiguration INSTANCE;
    private static boolean changed;

    private int editorScale;
    private String guapiTheme;
    private boolean guapiDebugMode;

    private ClientConfiguration() {
        editorScale = -1;
        guapiTheme = "vanilla";
        guapiDebugMode = false;
    }

    public int getEditorScale() {
        return editorScale;
    }

    public void setEditorScale(int editorScale) {
        if (this.editorScale != editorScale) {
            this.editorScale = editorScale;
            changed = true;
        }
    }

    public String getGuapiTheme() {
        return guapiTheme;
    }

    public void setGuapiTheme(String guapiTheme) {
        if (!this.guapiTheme.equals(guapiTheme)) {
            this.guapiTheme = guapiTheme;
            changed = true;
        }
    }

    public boolean isGuapiDebugMode() {
        return guapiDebugMode;
    }

    public void setGuapiDebugMode(boolean guapiDebugMode) {
        if (this.guapiDebugMode != guapiDebugMode) {
            this.guapiDebugMode = guapiDebugMode;
            changed = true;
        }
    }

    public static void load() {
        if (Files.exists(CLIENT_CONFIG_FILE)) {
            try (Reader r = Files.newBufferedReader(CLIENT_CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(r, ClientConfiguration.class);
                LOGGER.debug("Client configuration loaded");
            } catch (IOException e) {
                LOGGER.error("Error while loading client configuration", e);
            }
        } else {
            LOGGER.debug("Generating default client configuration");
            INSTANCE = new ClientConfiguration();
            changed = true;
            save();
        }
    }

    public static void save() {
        if (changed) {
            try (Writer w = Files.newBufferedWriter(CLIENT_CONFIG_FILE)) {
                GSON.toJson(INSTANCE, w);
                changed = false;
                LOGGER.debug("Client configuration saved");
            } catch (IOException e) {
                LOGGER.error("Error while saving client configuration", e);
            }
        }
    }
}
