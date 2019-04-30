package com.github.franckyi.ibeeditor.common;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;

public final class IBEConfiguration {

    public static final Client CLIENT;
    public static final ForgeConfigSpec clientSpec;

    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        clientSpec = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
        LogManager.getLogger().debug("Loaded IBE Editor config file {}", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.ConfigReloading configEvent) {
        LogManager.getLogger().fatal("IBE Editor config just got changed on the file system!");
    }

    public static class Client {

        public final ForgeConfigSpec.BooleanValue doesGuiPauseGame;
        public final ForgeConfigSpec.BooleanValue appendFormatCharAtCursor;
        public final ForgeConfigSpec.BooleanValue showEditorNotifications;
        public final ForgeConfigSpec.BooleanValue showClipboardNotifications;

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
            builder.pop();
        }
    }


}
