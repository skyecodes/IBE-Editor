package com.github.franckyi.ibeeditor.common;

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

import java.util.function.Function;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public final class ServerCommandHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MutableComponent MUST_INSTALL = text("You must install IBE Editor in order to use this command.").withStyle(ChatFormatting.RED);
    private static final MutableComponent DOWNLOAD = text("Click here to download the mod!").withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/ibe-editor"))).withStyle(ChatFormatting.AQUA, ChatFormatting.UNDERLINE);
    private static final MutableComponent NO_PERMISSION = text("You must be in creative mode to use this command.").withStyle(ChatFormatting.RED);

    private enum EditorTargetArgument {
        ITEM("item", EditorContext.CommandTarget.ITEM),
        BLOCK("block", EditorContext.CommandTarget.BLOCK),
        ENTITY("entity", EditorContext.CommandTarget.ENTITY),
        SELF("self", EditorContext.CommandTarget.SELF);

        private static final EditorContext.CommandTarget DEFAULT = EditorContext.CommandTarget.WORLD;
        private final String literal;
        private final EditorContext.CommandTarget target;

        EditorTargetArgument(String literal, EditorContext.CommandTarget target) {
            this.literal = literal;
            this.target = target;
        }
    }

    private enum EditorTypeArgument {
        NBT("nbt", EditorContext.EditorType.NBT),
        SNBT("snbt", EditorContext.EditorType.SNBT);

        private static final EditorContext.EditorType DEFAULT = EditorContext.EditorType.STANDARD;
        private final String literal;
        private final EditorContext.EditorType type;

        EditorTypeArgument(String literal, EditorContext.EditorType type) {
            this.literal = literal;
            this.type = type;
        }
    }

    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        LOGGER.debug("Registering /ibe command");
        LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("ibe").executes(
                createCommand(p -> commandOpenEditor(p, EditorTargetArgument.DEFAULT, EditorTypeArgument.DEFAULT)));
        for (EditorTypeArgument typeArg : EditorTypeArgument.values()) {
            LiteralArgumentBuilder<CommandSourceStack> subCommand = Commands.literal(typeArg.literal).executes(
                    createCommand(p -> commandOpenEditor(p, EditorTargetArgument.DEFAULT, typeArg.type)));
            for (EditorTargetArgument targetArg : EditorTargetArgument.values()) {
                subCommand.then(Commands.literal(targetArg.literal).executes(
                        createCommand(p -> commandOpenEditor(p, targetArg.target, typeArg.type))));
            }
            command.then(subCommand);
        }
        for (EditorTargetArgument targetArg : EditorTargetArgument.values()) {
            LiteralArgumentBuilder<CommandSourceStack> subCommand = Commands.literal(targetArg.literal).executes(
                    createCommand(p -> commandOpenEditor(p, targetArg.target, EditorTypeArgument.DEFAULT)));
            for (EditorTypeArgument typeArg : EditorTypeArgument.values()) {
                subCommand.then(Commands.literal(typeArg.literal).executes(
                        createCommand(p -> commandOpenEditor(p, targetArg.target, typeArg.type))));
            }
            command.then(subCommand);
        }
        command.requires(source -> source.hasPermission(CommonConfiguration.INSTANCE.getPermissionLevel()));
        dispatcher.register(command);
    }

    private static Command<CommandSourceStack> createCommand(Function<ServerPlayer, Integer> command) {
        return ctx -> command.apply(ctx.getSource().getPlayerOrException());
    }

    private static int commandOpenEditor(ServerPlayer player, EditorContext.CommandTarget target, EditorContext.EditorType type) {
        if (ServerContext.isClientModded(player)) {
            if (CommonConfiguration.INSTANCE.isCreativeOnly() && !player.isCreative()) {
                player.displayClientMessage(NO_PERMISSION, false);
                return 2;
            }
            var ctx = EditorContext.fromCommand();
            ctx.setCommandTarget(target);
            ctx.setEditorType(type);
            ServerNetworkEmitter.sendEditorCommand(player, ctx);
            return 0;
        }
        player.displayClientMessage(MUST_INSTALL, false);
        player.displayClientMessage(DOWNLOAD, false);
        return 1;
    }
}
