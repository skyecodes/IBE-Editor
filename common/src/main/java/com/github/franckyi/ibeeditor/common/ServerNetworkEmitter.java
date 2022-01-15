package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.ibeeditor.common.network.ClientNetworkHandler;
import com.github.franckyi.ibeeditor.common.network.NotificationPacket;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerNetworkEmitter {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void sendNotification(ServerPlayer player) {
        send(NetworkManager.SERVER_NOTIFICATION, player, new NotificationPacket());
    }

    public static void sendEditorCommand(ServerPlayer player, EditorContext ctx) {
        ctx.setStatus(EditorContext.Status.COMMAND);
        send(NetworkManager.EDITOR_COMMAND, player, ctx);
    }

    public static void sendEditorResponse(ServerPlayer player, EditorContext ctx) {
        ctx.setStatus(EditorContext.Status.RESPONSE);
        send(NetworkManager.EDITOR_RESPONSE, player, ctx);
    }

    private static <P> void send(ClientNetworkHandler<P> handler, ServerPlayer player, P packet) {
        LOGGER.debug("Sending packet {} to player {}", handler.getLocation(), player.getGameProfile().getName());
        NetworkManager.sendToClient(handler, player, packet);
    }
}
