package com.github.franckyi.ibeeditor.base.server;

import com.github.franckyi.gameadapter.api.common.IPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onPlayerJoin(IPlayer player) {
        LOGGER.debug("Notifying {} that this server has IBE Editor", player);
        ServerNetworkEmitter.sendServerNotification(player);
    }

    public static void onPlayerLeave(IPlayer player) {
        LOGGER.debug("Player {} left the server", player);
        ServerContext.removeModdedClient(player);
    }
}
