package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.gameadapter.api.common.world.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class ServerContext {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Set<UUID> moddedClients = new HashSet<>();

    public static void removeModdedClient(Player player) {
        LOGGER.debug("Removing {} from modded clients", player.toString());
        moddedClients.remove(player.getProfileId());
    }

    public static void addModdedClient(Player player) {
        LOGGER.debug("Adding {} to modded clients", player.toString());
        moddedClients.add(player.getProfileId());
    }

    public static boolean isClientModded(Player player) {
        return moddedClients.contains(player.getProfileId());
    }
}
