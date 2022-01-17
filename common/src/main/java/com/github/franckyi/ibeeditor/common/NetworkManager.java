package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.ibeeditor.PlatformUtil;
import com.github.franckyi.ibeeditor.client.ClientNetworkReceiver;
import com.github.franckyi.ibeeditor.common.network.ClientNetworkHandler;
import com.github.franckyi.ibeeditor.common.network.EditorContextSerializer;
import com.github.franckyi.ibeeditor.common.network.NotificationPacket;
import com.github.franckyi.ibeeditor.common.network.ServerNetworkHandler;
import net.minecraft.server.level.ServerPlayer;

public final class NetworkManager {
    public static final ClientNetworkHandler<NotificationPacket> SERVER_NOTIFICATION = new ClientNetworkHandler<>(NotificationPacket.class, "ibeeditor:network/server_notification", NotificationPacket.Serializer.INSTANCE, ClientNetworkReceiver::onServerNotification);
    public static final ServerNetworkHandler<NotificationPacket> CLIENT_NOTIFICATION = new ServerNetworkHandler<>(NotificationPacket.class, "ibeeditor:network/client_notification", NotificationPacket.Serializer.INSTANCE, ServerNetworkReceiver::onClientNotification);
    public static final ClientNetworkHandler<EditorContext> EDITOR_COMMAND = new ClientNetworkHandler<>(EditorContext.class, "ibeeditor:network/editor_command", EditorContextSerializer.INSTANCE, ClientNetworkReceiver::onEditorCommand);
    public static final ServerNetworkHandler<EditorContext> EDITOR_REQUEST = new ServerNetworkHandler<>(EditorContext.class, "ibeeditor:network/editor_request", EditorContextSerializer.INSTANCE, ServerNetworkReceiver::onEditorRequest);
    public static final ClientNetworkHandler<EditorContext> EDITOR_RESPONSE = new ClientNetworkHandler<>(EditorContext.class, "ibeeditor:network/editor_response", EditorContextSerializer.INSTANCE, ClientNetworkReceiver::onEditorResponse);
    public static final ServerNetworkHandler<EditorContext> EDITOR_UPDATE = new ServerNetworkHandler<>(EditorContext.class, "ibeeditor:network/editor_update", EditorContextSerializer.INSTANCE, ServerNetworkReceiver::onEditorUpdate);

    public static void setup() {
        PlatformUtil.registerClientHandler(SERVER_NOTIFICATION);
        PlatformUtil.registerServerHandler(CLIENT_NOTIFICATION);
        PlatformUtil.registerClientHandler(EDITOR_COMMAND);
        PlatformUtil.registerServerHandler(EDITOR_REQUEST);
        PlatformUtil.registerClientHandler(EDITOR_RESPONSE);
        PlatformUtil.registerServerHandler(EDITOR_UPDATE);
    }

    public static <P> void sendToServer(ServerNetworkHandler<P> handler, P packet) {
        PlatformUtil.sendToServer(handler, packet);
    }

    public static <P> void sendToClient(ClientNetworkHandler<P> handler, ServerPlayer player, P packet) {
        PlatformUtil.sendToClient(handler, player, packet);
    }
}
