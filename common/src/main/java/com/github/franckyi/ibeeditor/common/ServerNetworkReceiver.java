package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.ibeeditor.common.network.NotificationPacket;
import com.github.franckyi.ibeeditor.common.network.ServerNetworkHandler;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerNetworkReceiver {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onClientNotification(NotificationPacket packet, ServerPlayer player) {
        log(NetworkManager.CLIENT_NOTIFICATION, player);
        ServerContext.addModdedClient(player);
    }

    public static void onEditorRequest(EditorContext context, ServerPlayer player) {
        log(NetworkManager.EDITOR_REQUEST, player);
        ServerEditorLogic.processEditorRequest(context, player);
    }

    public static void onEditorUpdate(EditorContext context, ServerPlayer player) {
        log(NetworkManager.EDITOR_UPDATE, player);
        ServerEditorLogic.update(context, player);
    }

    private static void log(ServerNetworkHandler<?> handler, ServerPlayer player) {
        LOGGER.debug("Receiving packet {} from player {}", handler.getLocation(), player.getGameProfile().getName());
    }
}
