package com.github.franckyi.ibeeditor.util;

import com.github.franckyi.ibeeditor.IBEEditor;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextComponentString;

public class ChatUtil {

    public static void send(EntityPlayerSP player, String text, ChatFormatting... formatting) {
        StringBuilder builder = new StringBuilder();
        for (ChatFormatting chatFormatting : formatting) {
            builder.append(chatFormatting);
        }
        builder.append("[").append(IBEEditor.MODID).append("] ").append(text);
        player.sendMessage(new TextComponentString(builder.toString()));
    }

    public static void sendError(EntityPlayerSP player, String text) {
        send(player, text, ChatFormatting.RED);
    }

    public static void sendInfo(EntityPlayerSP player, String text) {
        send(player, text, ChatFormatting.BLUE);
    }

    public static void sendSuccess(EntityPlayerSP player, String text) {
        send(player, text, ChatFormatting.GREEN);
    }

    public static void sendWarn(EntityPlayerSP player, String text) {
        send(player, text, ChatFormatting.YELLOW);
    }

}
