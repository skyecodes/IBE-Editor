package com.github.franckyi.ibeeditor.common;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class CommonUtil {
    public static void showMessage(Player player, Component component) {
        player.displayClientMessage(component, false);
    }

    public static void showSuccess(Player player, MutableComponent component) {
        showMessage(player, ModTexts.Messages.successUpdate(component));
    }

    public static void showTargetError(Player player, MutableComponent component) {
        showMessage(player, ModTexts.Messages.errorNoTargetFound(component));
    }

    public static void showPermissionError(Player player, MutableComponent component) {
        showMessage(player, ModTexts.Messages.errorPermissionDenied(component));
    }
}
