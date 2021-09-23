package com.github.franckyi.ibeeditor.base.common;

import com.github.franckyi.ibeeditor.PlatformUtil;
import com.github.franckyi.ibeeditor.base.client.ClientNetworkReceiver;
import com.github.franckyi.ibeeditor.base.common.packet.*;
import com.github.franckyi.ibeeditor.base.server.ServerNetworkReceiver;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public final class NetworkManager {
    private static int i = 0;
    public static final String SERVER_NOTIFICATION = "ibeeditor:network/server_notification";
    public static final String CLIENT_NOTIFICATION = "ibeeditor:network/client_notification";

    public static final String PLAYER_MAIN_HAND_ITEM_UPDATE = "ibeeditor:network/player_main_hand_item_update";
    public static final String PLAYER_INVENTORY_ITEM_UPDATE = "ibeeditor:network/player_inventory_item_update";
    public static final String BLOCK_INVENTORY_ITEM_UPDATE = "ibeeditor:network/block_inventory_item_update";
    public static final String BLOCK_UPDATE = "ibeeditor:network/block_update";
    public static final String ENTITY_UPDATE = "ibeeditor:network/entity_update";

    public static final String BLOCK_EDITOR_REQUEST = "ibeeditor:network/block_editor_request";
    public static final String BLOCK_EDITOR_RESPONSE = "ibeeditor:network/block_editor_response";
    public static final String ENTITY_EDITOR_REQUEST = "ibeeditor:network/entity_editor_request";
    public static final String ENTITY_EDITOR_RESPONSE = "ibeeditor:network/entity_editor_response";

    public static final String EDITOR_COMMAND = "ibeeditor:network/editor_command";

    public static void setup() {
        PlatformUtil.registerClientHandler(SERVER_NOTIFICATION, i++, ServerNotificationPacket.class, ServerNotificationPacket::new, ClientNetworkReceiver::onServerNotification);
        PlatformUtil.registerServerHandler(CLIENT_NOTIFICATION, i++, ClientNotificationPacket.class, ClientNotificationPacket::new, ServerNetworkReceiver::onClientNotification);
        PlatformUtil.registerServerHandler(PLAYER_MAIN_HAND_ITEM_UPDATE, i++, PlayerMainHandItemUpdatePacket.class, PlayerMainHandItemUpdatePacket::new, ServerNetworkReceiver::onPlayerMainHandItemUpdate);
        PlatformUtil.registerServerHandler(PLAYER_INVENTORY_ITEM_UPDATE, i++, PlayerInventoryItemUpdatePacket.class, PlayerInventoryItemUpdatePacket::new, ServerNetworkReceiver::onPlayerInventoryItemUpdate);
        PlatformUtil.registerServerHandler(BLOCK_INVENTORY_ITEM_UPDATE, i++, BlockInventoryItemUpdatePacket.class, BlockInventoryItemUpdatePacket::new, ServerNetworkReceiver::onBlockInventoryItemUpdate);
        PlatformUtil.registerServerHandler(BLOCK_UPDATE, i++, BlockUpdatePacket.class, BlockUpdatePacket::new, ServerNetworkReceiver::onBlockUpdate);
        PlatformUtil.registerServerHandler(ENTITY_UPDATE, i++, EntityUpdatePacket.class, EntityUpdatePacket::new, ServerNetworkReceiver::onEntityUpdate);
        PlatformUtil.registerServerHandler(BLOCK_EDITOR_REQUEST, i++, BlockEditorRequestPacket.class, BlockEditorRequestPacket::new, ServerNetworkReceiver::onBlockEditorRequest);
        PlatformUtil.registerClientHandler(BLOCK_EDITOR_RESPONSE, i++, BlockEditorResponsePacket.class, BlockEditorResponsePacket::new, ClientNetworkReceiver::onBlockEditorResponse);
        PlatformUtil.registerServerHandler(ENTITY_EDITOR_REQUEST, i++, EntityEditorRequestPacket.class, EntityEditorRequestPacket::new, ServerNetworkReceiver::onEntityEditorRequest);
        PlatformUtil.registerClientHandler(ENTITY_EDITOR_RESPONSE, i++, EntityEditorResponsePacket.class, EntityEditorResponsePacket::new, ClientNetworkReceiver::onEntityEditorResponse);
        PlatformUtil.registerClientHandler(EDITOR_COMMAND, i++, EditorCommandPacket.class, EditorCommandPacket::new, ClientNetworkReceiver::onEditorCommand);
    }

    public static void sendToServer(String id, Packet packet) {
        PlatformUtil.sendToServer(id, packet);
    }

    public static void sendToClient(String id, ServerPlayer player, Packet packet) {
        PlatformUtil.sendToClient(id, player, packet);
    }

    @FunctionalInterface
    public interface PacketReader<P> {
        P read(FriendlyByteBuf buffer) throws Exception;
    }

    @FunctionalInterface
    public interface ClientPacketHandler<P> {
        void accept(P packet);
    }

    @FunctionalInterface
    public interface ServerPacketHandler<P> {
        void accept(P packet, ServerPlayer sender);
    }
}
