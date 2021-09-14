package com.github.franckyi.ibeeditor.base.server;

import com.github.franckyi.gameadapter.api.common.IPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class ServerContext {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Set<UUID> moddedClients = new HashSet<>();

    public static void removeModdedClient(IPlayer player) {
        LOGGER.debug("Removing {} from modded clients", player.getProfileName());
        moddedClients.remove(player.getProfileId());
    }

    public static void addModdedClient(IPlayer player) {
        LOGGER.debug("Adding {} to modded clients", player.getProfileName());
        moddedClients.add(player.getProfileId());
    }

    public static boolean isClientModded(IPlayer player) {
        return moddedClients.contains(player.getProfileId());
    }
}
