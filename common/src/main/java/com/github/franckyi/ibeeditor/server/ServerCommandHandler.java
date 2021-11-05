package com.github.franckyi.ibeeditor.server;

import com.github.franckyi.ibeeditor.common.EditorType;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public final class ServerCommandHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MutableComponent MUST_INSTALL = text("You must install IBE Editor in order to use this command.").withStyle(ChatFormatting.RED);
    private static final MutableComponent DOWNLOAD = text("Click here to download the mod!").withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/ibe-editor"))).withStyle(ChatFormatting.AQUA, ChatFormatting.UNDERLINE);

    private enum EditorTargetArgument {
        ITEM("item", ServerCommandHandler::commandOpenItemEditor),
        BLOCK("block", ServerCommandHandler::commandOpenBlockEditor),
        ENTITY("entity", ServerCommandHandler::commandOpenEntityEditor),
        SELF("self", ServerCommandHandler::commandOpenSelfEditor);

        private static final BiFunction<ServerPlayer, EditorType, Integer> DEFAULT = ServerCommandHandler::commandOpenWorldEditor;
        private final String literal;
        private final BiFunction<ServerPlayer, EditorType, Integer> target;

        EditorTargetArgument(String literal, BiFunction<ServerPlayer, EditorType, Integer> target) {
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

    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        LOGGER.debug("Registering /ibe command");
        LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("ibe").executes(
                createCommand(p -> EditorTargetArgument.DEFAULT.apply(p, EditorTypeArgument.DEFAULT)));
        for (EditorTypeArgument typeArg : EditorTypeArgument.values()) {
            LiteralArgumentBuilder<CommandSourceStack> subCommand = Commands.literal(typeArg.literal).executes(
                    createCommand(p -> EditorTargetArgument.DEFAULT.apply(p, typeArg.type)));
            for (EditorTargetArgument targetArg : EditorTargetArgument.values()) {
                subCommand.then(Commands.literal(targetArg.literal).executes(
                        createCommand(p -> targetArg.target.apply(p, typeArg.type))));
            }
            command.then(subCommand);
        }
        for (EditorTargetArgument targetArg : EditorTargetArgument.values()) {
            LiteralArgumentBuilder<CommandSourceStack> subCommand = Commands.literal(targetArg.literal).executes(
                    createCommand(p -> targetArg.target.apply(p, EditorTypeArgument.DEFAULT)));
            for (EditorTypeArgument typeArg : EditorTypeArgument.values()) {
                subCommand.then(Commands.literal(typeArg.literal).executes(
                        createCommand(p -> targetArg.target.apply(p, typeArg.type))));
            }
            command.then(subCommand);
        }
        dispatcher.register(command);
    }

    private static Command<CommandSourceStack> createCommand(Function<ServerPlayer, Integer> command) {
        return ctx -> command.apply(ctx.getSource().getPlayerOrException());
    }

    private static int commandOpenWorldEditor(ServerPlayer player, EditorType type) {
        LOGGER.debug("{} issued a world editor command with type={}", player.getGameProfile().getName(), type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendWorldEditorCommand);
    }

    private static int commandOpenItemEditor(ServerPlayer player, EditorType type) {
        LOGGER.debug("{} issued an item editor command with type={}", player.getGameProfile().getName(), type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendItemEditorCommand);
    }

    private static int commandOpenBlockEditor(ServerPlayer player, EditorType type) {
        LOGGER.debug("{} issued a block editor command with type={}", player.getGameProfile().getName(), type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendBlockEditorCommand);
    }

    private static int commandOpenEntityEditor(ServerPlayer player, EditorType type) {
        LOGGER.debug("{} issued an entity editor command with type={}", player.getGameProfile().getName(), type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendEntityEditorCommand);
    }

    private static int commandOpenSelfEditor(ServerPlayer player, EditorType type) {
        LOGGER.debug("{} issued a self editor command with type={}", player.getGameProfile().getName(), type);
        return commandOpenEditor(player, type, ServerNetworkEmitter::sendSelfEditorCommand);
    }

    private static int commandOpenEditor(ServerPlayer player, EditorType type, BiConsumer<ServerPlayer, EditorType> action) {
        if (ServerContext.isClientModded(player)) {
            action.accept(player, type);
            return 0;
        }
        player.displayClientMessage(MUST_INSTALL, false);
        player.displayClientMessage(DOWNLOAD, false);
        return 1;
    }
}
