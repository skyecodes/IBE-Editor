package com.github.franckyi.ibeeditor.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Paths;

public final class IBEEditorConfig {

    public static final Client CLIENT = new Client();
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec SPEC = BUILDER.build();

    public static void load() {
        SPEC.setConfig(CommentedFileConfig
                .builder(Paths.get("config", "ibeeditor.toml"))
                .build());
    }

    public static class Client {

        public final ForgeConfigSpec.BooleanValue doesGuiPauseGame;
        public final ForgeConfigSpec.BooleanValue appendFormatCharAtCursor;

        private Client() {
            BUILDER.push("client");
            doesGuiPauseGame = BUILDER
                    .comment("If true, will pause the game while the player is in the editor. Only works in Singleplayer mode.")
                    .define("doesGuiPauseGame", true);
            appendFormatCharAtCursor = BUILDER
                    .comment("If true, will append the formatting '§' character at the cursor position instead of the end of the field.")
                    .define("appendFormatCharAtCursor", false);
            BUILDER.pop();
        }
    }

}
