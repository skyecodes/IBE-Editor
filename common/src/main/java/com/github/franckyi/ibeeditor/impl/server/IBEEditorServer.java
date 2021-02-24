package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.ServerPlayer;
import com.github.franckyi.gamehooks.util.common.text.Text;
import com.github.franckyi.gamehooks.util.common.text.TextFormatting;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

public final class IBEEditorServer {
    private static final Set<UUID> allowedPlayers = new HashSet<>();
    private static final Text MUST_INSTALL = Text.literal("You must install IBE Editor in order to use this command.", TextFormatting.RED);
    private static final Text DOWNLOAD = Text.literal("Click here to download the mod!", "https://www.curseforge.com/minecraft/mc-mods/ibe-editor", TextFormatting.AQUA, TextFormatting.UNDERLINE);

    public static <S> void registerCommand(CommandDispatcher<S> dispatcher) {
        dispatcher.register(
                LiteralArgumentBuilder.<S>literal("ibe").executes(command(p -> triggerOpenWorldEditor(p, false)))
                        .then(LiteralArgumentBuilder.<S>literal("nbt").executes(command(p -> triggerOpenWorldEditor(p, true)))
                                .then(LiteralArgumentBuilder.<S>literal("item").executes(command(p -> triggerOpenItemEditor(p, true))))
                                .then(LiteralArgumentBuilder.<S>literal("block").executes(command(p -> triggerOpenBlockEditor(p, true))))
                                .then(LiteralArgumentBuilder.<S>literal("entity").executes(command(p -> triggerOpenEntityEditor(p, true))))
                                .then(LiteralArgumentBuilder.<S>literal("self").executes(command(p -> triggerOpenSelfEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("item").executes(command(p -> triggerOpenItemEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(command(p -> triggerOpenItemEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("block").executes(command(p -> triggerOpenBlockEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(command(p -> triggerOpenBlockEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("entity").executes(command(p -> triggerOpenEntityEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(command(p -> triggerOpenEntityEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("self").executes(command(p -> triggerOpenSelfEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(command(p -> triggerOpenSelfEditor(p, true)))))
        );
    }

    public static void notifyClient(ServerPlayer player) {
        ServerNetwork.notifyClient(player);
    }

    public static void removeAllowedPlayer(ServerPlayer player) {
        allowedPlayers.remove(player.getProfileId());
    }

    public static void addAllowedPlayer(ServerPlayer player) {
        allowedPlayers.add(player.getProfileId());
    }

    public static boolean isPlayerAllowed(ServerPlayer player) {
        return allowedPlayers.contains(player.getProfileId());
    }

    private static int triggerOpenWorldEditor(ServerPlayer player, boolean nbt) {
        return triggerOpenEditor(player, nbt, ServerNetwork::triggerOpenWorldEditor);
    }

    private static int triggerOpenItemEditor(ServerPlayer player, boolean nbt) {
        return triggerOpenEditor(player, nbt, ServerNetwork::triggerOpenItemEditor);
    }

    private static int triggerOpenBlockEditor(ServerPlayer player, boolean nbt) {
        return triggerOpenEditor(player, nbt, ServerNetwork::triggerOpenBlockEditor);
    }

    private static int triggerOpenEntityEditor(ServerPlayer player, boolean nbt) {
        return triggerOpenEditor(player, nbt, ServerNetwork::triggerOpenEntityEditor);
    }

    private static int triggerOpenSelfEditor(ServerPlayer player, boolean nbt) {
        return triggerOpenEditor(player, nbt, ServerNetwork::triggerOpenSelfEditor);
    }

    private static int triggerOpenEditor(ServerPlayer player, boolean nbt, BiConsumer<ServerPlayer, Boolean> action) {
        if (isPlayerAllowed(player)) {
            action.accept(player, nbt);
            return 0;
        }
        player.sendMessage(MUST_INSTALL);
        player.sendMessage(DOWNLOAD);
        return 1;
    }

    @SuppressWarnings("unchecked")
    private static <S> Command<S> command(Function<ServerPlayer, Integer> command) {
        return (Command<S>) GameHooks.common().command(command);
    }
}
