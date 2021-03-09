package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.minecraft.Minecraft;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.franckyi.ibeeditor.impl.common.IBEEditorCommon.LOGGER;

public final class IBEEditorConfiguration {
    private static final Marker MARKER = MarkerManager.getMarker("Config");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CLIENT_CONFIG_FILE = Minecraft.getCommon().getGameDir().resolve("config").resolve("ibeeditor-client.json");
    private static final Path COMMON_CONFIG_FILE = Minecraft.getCommon().getGameDir().resolve("config").resolve("ibeeditor-common.json");
    public static Client CLIENT;
    private static boolean clientChanged;
    public static Common COMMON;
    private static boolean commonChanged;

    private IBEEditorConfiguration() {
    }

    public static void loadClient() {
        if (Files.exists(CLIENT_CONFIG_FILE)) {
            try (Reader r = Files.newBufferedReader(CLIENT_CONFIG_FILE)) {
                CLIENT = GSON.fromJson(r, Client.class);
                LOGGER.debug(MARKER, "Client configuration loaded");
            } catch (IOException e) {
                LOGGER.error(MARKER, "Error while loading client configuration", e);
            }
        } else {
            LOGGER.debug(MARKER, "Generating default client configuration");
            CLIENT = new Client();
            clientChanged = true;
            saveClient();
        }
    }

    public static void saveClient() {
        if (clientChanged) {
            try (Writer w = Files.newBufferedWriter(CLIENT_CONFIG_FILE)) {
                GSON.toJson(CLIENT, w);
                clientChanged = false;
                LOGGER.debug(MARKER, "Client configuration saved");
            } catch (IOException e) {
                LOGGER.error(MARKER, "Error while saving client configuration", e);
            }
        }
    }

    public static void loadCommon() {
        if (Files.exists(COMMON_CONFIG_FILE)) {
            try (Reader r = Files.newBufferedReader(COMMON_CONFIG_FILE)) {
                COMMON = GSON.fromJson(r, Common.class);
                LOGGER.debug(MARKER, "Common configuration loaded");
            } catch (IOException e) {
                LOGGER.error(MARKER, "Error while loading common configuration", e);
            }
        } else {
            LOGGER.debug(MARKER, "Generating default common configuration");
            COMMON = new Common();
            commonChanged = true;
            saveCommon();
        }
    }

    public static void saveCommon() {
        if (commonChanged) {
            try (Writer w = Files.newBufferedWriter(COMMON_CONFIG_FILE)) {
                GSON.toJson(COMMON, w);
                commonChanged = false;
                LOGGER.debug(MARKER, "Common configuration saved");
            } catch (IOException e) {
                LOGGER.error(MARKER, "Error while saving common configuration", e);
            }
        }
    }

    public static class Client {
        private int editorScale;

        public int getEditorScale() {
            return editorScale;
        }

        public void setEditorScale(int editorScale) {
            if (this.editorScale != editorScale) {
                this.editorScale = editorScale;
                clientChanged = true;
            }
        }
    }

    public static class Common {
    }
}
