package com.github.franckyi.ibeeditor.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.github.franckyi.ibeeditor.IBEEditorMod;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Paths;

public final class IBEEditorConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC = BUILDER.build();
    private static final Client CLIENT = new Client();
    public static boolean doesGuiPauseGame = true;
    public static boolean appendFormatCharAtCursor = false;
    public static boolean showEditorNotifications = true;
    public static boolean showClipboardNotifications = false;

    public static void load() {
        SPEC.setConfig(CommentedFileConfig
                .builder(Paths.get("config", IBEEditorMod.MODID + ".toml"))
                .build());
    }

    public static void refresh() {
        doesGuiPauseGame = CLIENT.doesGuiPauseGame.get();
        appendFormatCharAtCursor = CLIENT.appendFormatCharAtCursor.get();
        showEditorNotifications = CLIENT.showEditorNotifications.get();
        showClipboardNotifications = CLIENT.showClipboardNotifications.get();
    }

    private static class Client {

        private final ForgeConfigSpec.BooleanValue doesGuiPauseGame;
        private final ForgeConfigSpec.BooleanValue appendFormatCharAtCursor;
        private final ForgeConfigSpec.BooleanValue showEditorNotifications;
        private final ForgeConfigSpec.BooleanValue showClipboardNotifications;

        private Client() {
            BUILDER.comment("These configurations are only for the client side").push("client");
            doesGuiPauseGame = BUILDER
                    .comment("If true, will pause the game while the player is in the editor.", "Only works in Singleplayer mode.")
                    .define("doesGuiPauseGame", true);
            appendFormatCharAtCursor = BUILDER
                    .comment("If true, will append the formatting '§' character at the cursor position instead of the end of the field.")
                    .define("appendFormatCharAtCursor", false);
            showEditorNotifications = BUILDER
                    .comment("If true, notifications about the editor (modifications applied, command copied, etc..) will be shown at the top-left corner of the screen.")
                    .define("showEditorNotifications", true);
            showClipboardNotifications = BUILDER
                    .comment("If true, notifications about the clipboard (clipboard loaded from disk / saved to disk) will be shown at the top-left corner of the screen.")
                    .define("showClipboardNotifications", false);
            BUILDER.pop();
        }
    }

}
