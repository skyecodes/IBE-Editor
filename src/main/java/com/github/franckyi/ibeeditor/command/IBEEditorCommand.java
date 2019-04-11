package com.github.franckyi.ibeeditor.command;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.network.OpenEditorMessage;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.network.NetworkDirection;

public class IBEEditorCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(getCommand());
    }

    private static LiteralArgumentBuilder<CommandSource> getCommand() {
        LiteralArgumentBuilder<CommandSource> ibe = Commands.literal("ibe").executes(ctx -> execute(ctx, EditorArgument.ANY));
        for (EditorArgument argument : EditorArgument.values()) {
            if (argument != EditorArgument.ANY) {
                ibe.then(Commands.literal(argument.str).executes(ctx -> execute(ctx, argument)));
            }
        }
        return ibe;
    }

    private static int execute(CommandContext<CommandSource> ctx, EditorArgument argument) {
        try {
            IBEEditorMod.CHANNEL.sendTo(new OpenEditorMessage(argument), ctx.getSource().asPlayer().connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
        } catch (CommandSyntaxException e) {
            ctx.getSource().sendErrorMessage(new TextComponentString(TextFormatting.RED + "This command can only be executed by a player"));
            return 0;
        }
        return 1;
    }

    public enum EditorArgument {
        ANY("any"), ITEM("item"), BLOCK("block"), ENTITY("entity"), SELF("self");

        private final String str;

        EditorArgument(String str) {
            this.str = str;
        }

    }

}