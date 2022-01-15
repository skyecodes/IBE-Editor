package com.github.franckyi.ibeeditor.client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public final class ClientUtil {
    public static void showMessage(Component message) {
        Minecraft.getInstance().player.displayClientMessage(message, false);
    }
}
