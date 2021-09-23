package com.github.franckyi.ibeeditor.base.server;

import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class ServerContext {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Set<UUID> moddedClients = new HashSet<>();

    public static void removeModdedClient(ServerPlayer player) {
        LOGGER.debug("Removing {} from modded clients", player.getGameProfile().getName());
        moddedClients.remove(player.getGameProfile().getId());
    }

    public static void addModdedClient(ServerPlayer player) {
        LOGGER.debug("Adding {} to modded clients", player.getGameProfile().getName());
        moddedClients.add(player.getGameProfile().getId());
    }

    public static boolean isClientModded(ServerPlayer player) {
        return moddedClients.contains(player.getGameProfile().getId());
    }
}
