package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public final class IBEConfiguration {
    private static final Marker MARKER;
    public static final ForgeConfigSpec clientSpec;
    public static final Client CLIENT;

    static {
        MARKER = MarkerManager.getMarker("CONFIG");
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        clientSpec = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
        IBEEditorMod.LOGGER.info(MARKER, "Loaded IBE Editor configuration file {}", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading configEvent) {
        IBEEditorMod.LOGGER.info(MARKER, "Reloaded IBE Editor configuration file {}", configEvent.getConfig().getFileName());
    }

    public static class Client {
        public final ForgeConfigSpec.BooleanValue doesGuiPauseGame;
        public final ForgeConfigSpec.BooleanValue appendFormatCharAtCursor;
        public final ForgeConfigSpec.BooleanValue showEditorNotifications;
        public final ForgeConfigSpec.BooleanValue showClipboardNotifications;
        public final ForgeConfigSpec.BooleanValue creativeModeOnly;

        private Client(ForgeConfigSpec.Builder builder) {
            builder.comment("These configurations are only for the client side").push("client");
            doesGuiPauseGame = builder
                    .comment("If true, will pause the game while the player is in the editor.", "Only works in Singleplayer mode.")
                    .define("doesGuiPauseGame", true);
            appendFormatCharAtCursor = builder
                    .comment("If true, will append the formatting '§' character at the cursor position instead of the end of the field.")
                    .define("appendFormatCharAtCursor", false);
            showEditorNotifications = builder
                    .comment("If true, notifications about the editor (modifications applied, command copied, etc..) will be shown at the top-left corner of the screen.")
                    .define("showEditorNotifications", true);
            showClipboardNotifications = builder
                    .comment("If true, notifications about the clipboard (clipboard loaded from disk / saved to disk) will be shown at the top-left corner of the screen.")
                    .define("showClipboardNotifications", false);
            creativeModeOnly = builder
                    .comment("If true, the editor will only open if the player is in creative mode.")
                    .define("creativeModeOnly", true);
            builder.pop();
        }
    }
}
