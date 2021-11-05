package com.github.franckyi.ibeeditor.server;

import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onPlayerJoin(ServerPlayer player) {
        LOGGER.debug("Notifying {} that this server has IBE Editor", player.getGameProfile().getName());
        ServerNetworkEmitter.sendServerNotification(player);
    }

    public static void onPlayerLeave(ServerPlayer player) {
        LOGGER.debug("Player {} left the server", player.getGameProfile().getName());
        ServerContext.removeModdedClient(player);
    }
}
