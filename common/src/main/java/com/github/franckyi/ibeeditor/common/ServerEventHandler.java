package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.ibeeditor.common.network.ModNotificationPacket;
import com.github.franckyi.ibeeditor.common.network.NetworkManager;
import net.minecraft.server.level.ServerPlayer;

public final class ServerEventHandler {
    public static void onPlayerJoin(ServerPlayer player) {
        NetworkManager.sendToClient(player, NetworkManager.SERVER_NOTIFICATION, ModNotificationPacket.Server.INSTANCE);
    }

    public static void onPlayerLeave(ServerPlayer player) {
        ServerContext.removeModdedClient(player);
    }
}
