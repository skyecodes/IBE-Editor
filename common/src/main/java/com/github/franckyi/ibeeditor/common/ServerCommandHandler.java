package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.ibeeditor.common.logic.ServerEditorCommandLogic;
import com.github.franckyi.ibeeditor.common.network.EditorCommandPacket;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

public final class ServerCommandHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    private enum EditorTargetArgument {
        ITEM("item", EditorCommandPacket.Target.ITEM),
        BLOCK("block", EditorCommandPacket.Target.BLOCK),
        ENTITY("entity", EditorCommandPacket.Target.ENTITY),
        SELF("self", EditorCommandPacket.Target.SELF);

        private static final EditorCommandPacket.Target DEFAULT = EditorCommandPacket.Target.WORLD;
        private final String literal;
        private final EditorCommandPacket.Target target;

        EditorTargetArgument(String literal, EditorCommandPacket.Target target) {
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
                createCommand(p -> ServerEditorCommandLogic.commandOpenEditor(p, EditorTargetArgument.DEFAULT, EditorTypeArgument.DEFAULT)));
        for (EditorTypeArgument typeArg : EditorTypeArgument.values()) {
            LiteralArgumentBuilder<CommandSourceStack> subCommand = Commands.literal(typeArg.literal).executes(
                    createCommand(p -> ServerEditorCommandLogic.commandOpenEditor(p, EditorTargetArgument.DEFAULT, typeArg.type)));
            for (EditorTargetArgument targetArg : EditorTargetArgument.values()) {
                subCommand.then(Commands.literal(targetArg.literal).executes(
                        createCommand(p -> ServerEditorCommandLogic.commandOpenEditor(p, targetArg.target, typeArg.type))));
            }
            command.then(subCommand);
        }
        for (EditorTargetArgument targetArg : EditorTargetArgument.values()) {
            LiteralArgumentBuilder<CommandSourceStack> subCommand = Commands.literal(targetArg.literal).executes(
                    createCommand(p -> ServerEditorCommandLogic.commandOpenEditor(p, targetArg.target, EditorTypeArgument.DEFAULT)));
            for (EditorTypeArgument typeArg : EditorTypeArgument.values()) {
                subCommand.then(Commands.literal(typeArg.literal).executes(
                        createCommand(p -> ServerEditorCommandLogic.commandOpenEditor(p, targetArg.target, typeArg.type))));
            }
            command.then(subCommand);
        }
        command.requires(source -> source.hasPermission(CommonConfiguration.INSTANCE.getPermissionLevel()));
        dispatcher.register(command);
    }

    private static Command<CommandSourceStack> createCommand(Function<ServerPlayer, Integer> command) {
        return ctx -> command.apply(ctx.getSource().getPlayerOrException());
    }
}
