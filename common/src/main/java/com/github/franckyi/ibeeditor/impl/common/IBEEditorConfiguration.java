package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.gamehooks.GameHooks;
import com.google.gson.Gson;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class IBEEditorConfiguration {
    public static IBEEditorConfiguration INSTANCE;
    private static final Path CONFIG_FILE = GameHooks.common().gameDir().resolve("config").resolve("ibeeditor.json");
    private static final Marker MARKER = MarkerManager.getMarker("Config");
    private static final Gson GSON = new Gson();
    private static final IBEEditorConfiguration DEFAULT_CONFIG = new IBEEditorConfiguration();
    private static boolean changed;

    private int editorScale = -1;

    public IBEEditorConfiguration() {
    }

    public static void load() {
        if (Files.exists(CONFIG_FILE)) {
            try (Reader r = Files.newBufferedReader(CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(r, IBEEditorConfiguration.class);
                IBEEditor.LOGGER.info(MARKER, "Configuration loaded");
            } catch (IOException e) {
                IBEEditor.LOGGER.error(MARKER, "Error while loading configuration", e);
            }
        } else {
            IBEEditor.LOGGER.info(MARKER, "Generating default configuration");
            INSTANCE = DEFAULT_CONFIG;
            changed = true;
            save();
        }
    }

    public static void save() {
        if (changed) {
            try (Writer w = Files.newBufferedWriter(CONFIG_FILE)) {
                GSON.toJson(INSTANCE, w);
                changed = false;
                IBEEditor.LOGGER.info(MARKER, "Configuration saved");
            } catch (IOException e) {
                IBEEditor.LOGGER.error(MARKER, "Error while saving configuration", e);
            }
        }
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
}
