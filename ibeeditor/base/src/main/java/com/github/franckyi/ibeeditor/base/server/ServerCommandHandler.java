package com.github.franckyi.ibeeditor.base.server;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Player;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.github.franckyi.guapi.GuapiHelper.*;

public final class ServerCommandHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Text MUST_INSTALL = text("You must install IBE Editor in order to use this command.").red();
    private static final Text DOWNLOAD = text("Click here to download the mod!").click(link("https://www.curseforge.com/minecraft/mc-mods/ibe-editor")).aqua().underlined();

    private enum EditorTargetArgument {
        ITEM("item", ServerCommandHandler::commandOpenItemEditor),
        BLOCK("block", ServerCommandHandler::commandOpenBlockEditor),
        ENTITY("entity", ServerCommandHandler::commandOpenEntityEditor),
        SELF("self", ServerCommandHandler::commandOpenSelfEditor);

        private static final BiFunction<Player, EditorType, Integer> DEFAULT = ServerCommandHandler::commandOpenWorldEditor;
        private final String literal;
        private final BiFunction<Player, EditorType, Integer> target;

        EditorTargetArgument(String literal, BiFunction<Player, EditorType, Integer> target) {
            this.literal = literal;
            this.target = target;
        }
    }

    private enum EditorTypeArgument {
        NBT("nbt", EditorType.NBT),
        SNBT("snbt", EditorType.SNBT);

        private static final EditorType DEFAULT = EditorType.STANDARD;
        private final String literal;
        private final EditorType type;

        EditorTypeArgument(String literal, EditorType type) {
            this.literal = literal;
            this.type = type;
        }
    }

    public static <S> void registerCommand(CommandDispatcher<S> dispatcher) {
        LOGGER.debug("Registering /ibe command");
        LiteralArgumentBuilder<S> command = LiteralArgumentBuilder.<S>literal("ibe").executes(
                createCommand(p -> EditorTargetArgument.DEFAULT.apply(p, EditorTypeArgument.DEFAULT)));
        for (EditorTypeArgument typeArg : EditorTypeArgument.values()) {
            LiteralArgumentBuilder<S> subCommand = LiteralArgumentBuilder.<S>literal(typeArg.literal).executes(
                    createCommand(p -> EditorTargetArgument.DEFAULT.apply(p, typeArg.type)));
            for (EditorTargetArgument targetArg : EditorTargetArgument.values()) {
                subCommand.then(LiteralArgumentBuilder.<S>literal(targetArg.literal).executes(
                        createCommand(p -> targetArg.target.apply(p, typeArg.type))));
            }
            command.then(subCommand);
        }
        for (EditorTargetArgument targetArg : EditorTargetArgument.values()) {
            LiteralArgumentBuilder<S> subCommand = LiteralArgumentBuilder.<S>literal(targetArg.literal).executes(
                    createCommand(p -> targetArg.target.apply(p, EditorTypeArgument.DEFAULT)));
            for (EditorTypeArgument typeArg : EditorTypeArgument.values()) {
                subCommand.then(LiteralArgumentBuilder.<S>literal(typeArg.literal).executes(
                        createCommand(p -> targetArg.target.apply(p, typeArg.type))));
            }
            command.then(subCommand);
        }
        dispatcher.register(command);
    }

    @SuppressWarnings("unchecked")
    private static <S> Command<S> createCommand(Function<Player, Integer> command) {
        return (Command<S>) Game.getCommon().createCommand(command);
    }

    private static int commandOpenWorldEditor(Player player, EditorType type) {
        LOGGER.debug("{} issued a world editor command with type={}", player, type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendWorldEditorCommand);
    }

    private static int commandOpenItemEditor(Player player, EditorType type) {
        LOGGER.debug("{} issued an item editor command with type={}", player, type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendItemEditorCommand);
    }

    private static int commandOpenBlockEditor(Player player, EditorType type) {
        LOGGER.debug("{} issued a block editor command with type={}", player, type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendBlockEditorCommand);
    }

    private static int commandOpenEntityEditor(Player player, EditorType type) {
        LOGGER.debug("{} issued an entity editor command with type={}", player, type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendEntityEditorCommand);
    }

    private static int commandOpenSelfEditor(Player player, EditorType type) {
        LOGGER.debug("{} issued a self editor command with type={}", player, type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendSelfEditorCommand);
    }

    private static int commandOpenEditor(Player player, EditorType type, BiConsumer<Player, EditorType> action) {
        if (ServerContext.isClientModded(player)) {
            action.accept(player, type);
            return 0;
        }
        player.sendMessage(MUST_INSTALL);
        player.sendMessage(DOWNLOAD);
        return 1;
    }
}
