package com.github.franckyi.ibeeditor.common;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class CommonUtil {
    public static void showMessage(ServerPlayer player, Component component) {
        player.displayClientMessage(component, false);
    }
}
