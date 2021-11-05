package com.github.franckyi.ibeeditor.client.config;

import com.github.franckyi.guapi.api.util.DebugMode;
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

public final class ClientConfiguration {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CLIENT_CONFIG_FILE = PlatformUtil.getConfigDir().resolve("ibeeditor-client.json");
    public static ClientConfiguration INSTANCE;
    private static boolean changed;

    private final int version;
    private int editorScale;
    private String guapiTheme;
    private DebugMode guapiDebugMode;
    private int selectionScreenMaxItems;

    private ClientConfiguration() {
        version = 0;
        editorScale = -1;
        guapiTheme = "vanilla";
        guapiDebugMode = DebugMode.OFF;
        selectionScreenMaxItems = 100;
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

    public DebugMode getGuapiDebugMode() {
        return guapiDebugMode;
    }

    public void setGuapiDebugMode(DebugMode guapiDebugMode) {
        if (this.guapiDebugMode != guapiDebugMode) {
            this.guapiDebugMode = guapiDebugMode;
            changed = true;
        }
    }

    public int getSelectionScreenMaxItems() {
        return selectionScreenMaxItems;
    }

    public void setSelectionScreenMaxItems(int selectionScreenMaxItems) {
        if (this.selectionScreenMaxItems != selectionScreenMaxItems) {
            this.selectionScreenMaxItems = selectionScreenMaxItems;
            changed = true;
        }
    }

    public static void load() {
        if (Files.exists(CLIENT_CONFIG_FILE)) {
            try (Reader r = Files.newBufferedReader(CLIENT_CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(r, ClientConfiguration.class);
                LOGGER.info("Client configuration loaded");
                return;
            } catch (IOException | JsonSyntaxException e) {
                LOGGER.error("Error while loading client configuration", e);
                INSTANCE = new ClientConfiguration();
            }
        } else {
            LOGGER.info("Generating default client configuration");
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
                LOGGER.info("Client configuration saved");
            } catch (IOException e) {
                LOGGER.error("Error while saving client configuration", e);
            }
        }
    }
}
