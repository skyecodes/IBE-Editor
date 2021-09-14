package com.github.franckyi.ibeeditor.base.server;

import com.github.franckyi.gameadapter.api.Game;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.github.franckyi.guapi.api.GuapiHelper.link;
import static com.github.franckyi.guapi.api.GuapiHelper.text;

public final class ServerCommandHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final IText MUST_INSTALL = text("You must install IBE Editor in order to use this command.").red();
    private static final IText DOWNLOAD = text("Click here to download the mod!").click(link("https://www.curseforge.com/minecraft/mc-mods/ibe-editor")).aqua().underlined();

    private enum EditorTargetArgument {
        ITEM("item", ServerCommandHandler::commandOpenItemEditor),
        BLOCK("block", ServerCommandHandler::commandOpenBlockEditor),
        ENTITY("entity", ServerCommandHandler::commandOpenEntityEditor),
        SELF("self", ServerCommandHandler::commandOpenSelfEditor);

        private static final BiFunction<IPlayer, EditorType, Integer> DEFAULT = ServerCommandHandler::commandOpenWorldEditor;
        private final String literal;
        private final BiFunction<IPlayer, EditorType, Integer> target;

        EditorTargetArgument(String literal, BiFunction<IPlayer, EditorType, Integer> target) {
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
    private static <S> Command<S> createCommand(Function<IPlayer, Integer> command) {
        return (Command<S>) Game.getCommon().createCommand(command);
    }

    private static int commandOpenWorldEditor(IPlayer player, EditorType type) {
        LOGGER.debug("{} issued a world editor command with type={}", player.getProfileName(), type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendWorldEditorCommand);
    }

    private static int commandOpenItemEditor(IPlayer player, EditorType type) {
        LOGGER.debug("{} issued an item editor command with type={}", player.getProfileName(), type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendItemEditorCommand);
    }

    private static int commandOpenBlockEditor(IPlayer player, EditorType type) {
        LOGGER.debug("{} issued a block editor command with type={}", player.getProfileName(), type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendBlockEditorCommand);
    }

    private static int commandOpenEntityEditor(IPlayer player, EditorType type) {
        LOGGER.debug("{} issued an entity editor command with type={}", player.getProfileName(), type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendEntityEditorCommand);
    }

    private static int commandOpenSelfEditor(IPlayer player, EditorType type) {
        LOGGER.debug("{} issued a self editor command with type={}", player.getProfileName(), type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendSelfEditorCommand);
    }

    private static int commandOpenEditor(IPlayer player, EditorType type, BiConsumer<IPlayer, EditorType> action) {
        if (ServerContext.isClientModded(player)) {
            action.accept(player, type);
            return 0;
        }
        player.sendMessage(MUST_INSTALL);
        player.sendMessage(DOWNLOAD);
        return 1;
    }
}
