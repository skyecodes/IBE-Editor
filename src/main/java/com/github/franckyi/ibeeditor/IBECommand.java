package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.common.network.IBENetworkHandler;
import com.github.franckyi.ibeeditor.common.network.editor.OpenEditorMessage;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.fml.network.NetworkDirection;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class IBECommand {

    private static final ITextComponent DOWNLOAD_LINK = new StringTextComponent("Click here to download the mod !")
            .setStyle(Style.EMPTY.setUnderlined(true).setBold(true).setColor(Color.func_240744_a_(TextFormatting.BLUE))
                    .setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
                            "https://minecraft.curseforge.com/projects/ibe-editor")));
    private static final ITextComponent MUST_INSTALL = ITextComponent.func_244388_a(
            TextFormatting.RED + "You must install the IBE Editor mod to use this command.");
    private static final ITextComponent PLAYER_ONLY = ITextComponent.func_244388_a(
            TextFormatting.RED + "This command can only be executed by a player");


    private static Set<UUID> allowedPlayers = new HashSet<>();

    static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(getCommand());
    }

    private static LiteralArgumentBuilder<CommandSource> getCommand() {
        LiteralArgumentBuilder<CommandSource> ibe = Commands
                .literal("ibe")
                .executes(ctx -> execute(ctx, EditorArgument.ANY));
        for (EditorArgument argument : EditorArgument.values()) {
            if (argument != EditorArgument.ANY) {
                ibe.then(Commands.literal(argument.toString()).executes(ctx -> execute0(ctx, argument)));
            }
        }
        return ibe;
    }

    private static int execute0(CommandContext<CommandSource> ctx, EditorArgument argument) {
        if (argument != EditorArgument.ANY) {
            return execute(ctx, argument);
        }
        return 1;
    }

    private static int execute(CommandContext<CommandSource> ctx, EditorArgument argument) {
        try {
            if (allowedPlayers.contains(ctx.getSource().asPlayer().getUniqueID())) {
                IBENetworkHandler.getModChannel().sendTo(new OpenEditorMessage(argument),
                        ctx.getSource().asPlayer().connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
            } else {
                ctx.getSource().sendErrorMessage(MUST_INSTALL);
                ctx.getSource().sendErrorMessage(DOWNLOAD_LINK);
                IBEEditorMod.LOGGER.debug(ctx.getSource().asPlayer().getName().getUnformattedComponentText()
                        + " tried to open the editor without having the client mod installed.");
                return 0;
            }
        } catch (CommandSyntaxException e) {
            ctx.getSource().sendErrorMessage(PLAYER_ONLY);
            IBEEditorMod.LOGGER.debug(ctx.getSource() + " tried to eopen the editor, but it's not a player.");
            return 0;
        }
        return 1;
    }

    public static void addAllowedPlayer(PlayerEntity player) {
        if (allowedPlayers.add(player.getUniqueID())) {
            IBEEditorMod.LOGGER.debug("Adding " + player.getName().getUnformattedComponentText() + " to allowed players list.");
        }
    }

    public static void removeAllowedPlayer(PlayerEntity player) {
        if (allowedPlayers.remove(player.getUniqueID())) {
            IBEEditorMod.LOGGER.debug("Removing " + player.getName().getUnformattedComponentText() + " from allowed players list.");
        }
    }

    public enum EditorArgument {
        ANY("any"), ITEM("item"), BLOCK("block"), ENTITY("entity"), SELF("self");

        private final String str;

        EditorArgument(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }

}