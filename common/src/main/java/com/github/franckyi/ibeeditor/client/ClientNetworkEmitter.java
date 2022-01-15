package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.common.EditorContext;
import com.github.franckyi.ibeeditor.common.NetworkManager;
import com.github.franckyi.ibeeditor.common.network.NotificationPacket;
import com.github.franckyi.ibeeditor.common.network.ServerNetworkHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientNetworkEmitter {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void sendClientNotification() {
        send(NetworkManager.CLIENT_NOTIFICATION, new NotificationPacket());
    }

    public static void sendEditorRequest(EditorContext ctx) {
        ctx.setStatus(EditorContext.Status.REQUEST);
        send(NetworkManager.EDITOR_REQUEST, ctx);
    }

    public static void sendEditorUpdate(EditorContext ctx) {
        ctx.setStatus(EditorContext.Status.UPDATE);
        send(NetworkManager.EDITOR_UPDATE, ctx);
    }

    private static <P> void send(ServerNetworkHandler<P> handler, P packet) {
        LOGGER.debug("Sending packet {} to the server", handler);
        NetworkManager.sendToServer(handler, packet);
    }
}
