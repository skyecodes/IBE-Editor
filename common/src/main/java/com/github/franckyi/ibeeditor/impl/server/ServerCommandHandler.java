package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.world.Player;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public final class ServerCommandHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final Text MUST_INSTALL = text("You must install IBE Editor in order to use this command.").red();
    private static final Text DOWNLOAD = text("Click here to download the mod!").click(link("https://www.curseforge.com/minecraft/mc-mods/ibe-editor")).aqua().underlined();

    public static <S> void registerCommand(CommandDispatcher<S> dispatcher) {
        LOGGER.debug("Registering /ibe command");
        dispatcher.register(
                LiteralArgumentBuilder.<S>literal("ibe").executes(createCommand(p -> commandOpenWorldEditor(p, false)))
                        .then(LiteralArgumentBuilder.<S>literal("nbt").executes(createCommand(p -> commandOpenWorldEditor(p, true)))
                                .then(LiteralArgumentBuilder.<S>literal("item").executes(createCommand(p -> commandOpenItemEditor(p, true))))
                                .then(LiteralArgumentBuilder.<S>literal("block").executes(createCommand(p -> commandOpenBlockEditor(p, true))))
                                .then(LiteralArgumentBuilder.<S>literal("entity").executes(createCommand(p -> commandOpenEntityEditor(p, true))))
                                .then(LiteralArgumentBuilder.<S>literal("self").executes(createCommand(p -> commandOpenSelfEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("item").executes(createCommand(p -> commandOpenItemEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(createCommand(p -> commandOpenItemEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("block").executes(createCommand(p -> commandOpenBlockEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(createCommand(p -> commandOpenBlockEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("entity").executes(createCommand(p -> commandOpenEntityEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(createCommand(p -> commandOpenEntityEditor(p, true)))))
                        .then(LiteralArgumentBuilder.<S>literal("self").executes(createCommand(p -> commandOpenSelfEditor(p, false)))
                                .then(LiteralArgumentBuilder.<S>literal("nbt").executes(createCommand(p -> commandOpenSelfEditor(p, true)))))
        );
    }

    @SuppressWarnings("unchecked")
    private static <S> Command<S> createCommand(Function<Player, Integer> command) {
        return (Command<S>) Minecraft.getCommon().createCommand(command);
    }

    private static int commandOpenWorldEditor(Player player, boolean nbt) {
        LOGGER.debug("{} issued a world editor command with nbt={}", player, nbt);
        return commandOpenEditor(player, nbt, ServerNetworkEmitter::sendWorldEditorCommand);
    }

    private static int commandOpenItemEditor(Player player, boolean nbt) {
        LOGGER.debug("{} issued an item editor command with nbt={}", player, nbt);
        return commandOpenEditor(player, nbt, ServerNetworkEmitter::sendItemEditorCommand);
    }

    private static int commandOpenBlockEditor(Player player, boolean nbt) {
        LOGGER.debug("{} issued a block editor command with nbt={}", player, nbt);
        return commandOpenEditor(player, nbt, ServerNetworkEmitter::sendBlockEditorCommand);
    }

    private static int commandOpenEntityEditor(Player player, boolean nbt) {
        LOGGER.debug("{} issued an entity editor command with nbt={}", player, nbt);
        return commandOpenEditor(player, nbt, ServerNetworkEmitter::sendEntityEditorCommand);
    }

    private static int commandOpenSelfEditor(Player player, boolean nbt) {
        LOGGER.debug("{} issued a self editor command with nbt={}", player, nbt);
        return commandOpenEditor(player, nbt, ServerNetworkEmitter::sendSelfEditorCommand);
    }

    private static int commandOpenEditor(Player player, boolean nbt, BiConsumer<Player, Boolean> action) {
        if (ServerContext.isClientModded(player)) {
            action.accept(player, nbt);
            return 0;
        }
        player.sendMessage(MUST_INSTALL);
        player.sendMessage(DOWNLOAD);
        return 1;
    }
}
