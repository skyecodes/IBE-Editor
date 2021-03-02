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

import static com.github.franckyi.ibeeditor.impl.common.IBEEditorCommon.LOGGER;

public final class IBEEditorConfiguration {
    public static Client CLIENT;
    private static final Path CLIENT_CONFIG_FILE = GameHooks.common().getGameDir().resolve("config").resolve("ibeeditor-client.json");
    private static final Marker MARKER = MarkerManager.getMarker("Config");
    private static final Gson GSON = new Gson();
    private static final Client DEFAULT_CLIENT_CONFIG = new Client();

    private IBEEditorConfiguration() {
    }

    public static void loadClient() {
        if (Files.exists(CLIENT_CONFIG_FILE)) {
            try (Reader r = Files.newBufferedReader(CLIENT_CONFIG_FILE)) {
                CLIENT = GSON.fromJson(r, Client.class);
                LOGGER.info(MARKER, "Client configuration loaded");
            } catch (IOException e) {
                LOGGER.error(MARKER, "Error while loading client configuration", e);
            }
        } else {
            LOGGER.info(MARKER, "Generating default client configuration");
            CLIENT = DEFAULT_CLIENT_CONFIG;
            Client.changed = true;
            saveClient();
        }
    }

    public static void saveClient() {
        if (Client.changed) {
            try (Writer w = Files.newBufferedWriter(CLIENT_CONFIG_FILE)) {
                GSON.toJson(CLIENT, w);
                Client.changed = false;
                LOGGER.info(MARKER, "Client configuration saved");
            } catch (IOException e) {
                LOGGER.error(MARKER, "Error while saving client configuration", e);
            }
        }
    }

    public static class Client {
        private static boolean changed;
        private int editorScale;

        public int getEditorScale() {
            return editorScale;
        }

        public void setEditorScale(int editorScale) {
            this.editorScale = editorScale;
        }
    }
}
