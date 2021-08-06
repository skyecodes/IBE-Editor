package com.github.franckyi.ibeeditor.base.common;

import com.github.franckyi.ibeeditor.base.client.ClientNetworkReceiver;
import com.github.franckyi.ibeeditor.base.common.packet.*;
import com.github.franckyi.ibeeditor.base.server.ServerNetworkReceiver;

public final class ModNetwork {
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

    private static Network network;

    public static Network get() {
        return network;
    }

    public static void set(Network network) {
        ModNetwork.network = network;
    }

    public static void init() {
        network.registerClientHandler(SERVER_NOTIFICATION, i++, ServerNotificationPacket.class, ServerNotificationPacket::new, ClientNetworkReceiver::onServerNotification);
        network.registerServerHandler(CLIENT_NOTIFICATION, i++, ClientNotificationPacket.class, ClientNotificationPacket::new, ServerNetworkReceiver::onClientNotification);
        network.registerServerHandler(PLAYER_MAIN_HAND_ITEM_UPDATE, i++, PlayerMainHandItemUpdatePacket.class, PlayerMainHandItemUpdatePacket::new, ServerNetworkReceiver::onPlayerMainHandItemUpdate);
        network.registerServerHandler(PLAYER_INVENTORY_ITEM_UPDATE, i++, PlayerInventoryItemUpdatePacket.class, PlayerInventoryItemUpdatePacket::new, ServerNetworkReceiver::onPlayerInventoryItemUpdate);
        network.registerServerHandler(BLOCK_INVENTORY_ITEM_UPDATE, i++, BlockInventoryItemUpdatePacket.class, BlockInventoryItemUpdatePacket::new, ServerNetworkReceiver::onBlockInventoryItemUpdate);
        network.registerServerHandler(BLOCK_UPDATE, i++, BlockUpdatePacket.class, BlockUpdatePacket::new, ServerNetworkReceiver::onBlockUpdate);
        network.registerServerHandler(ENTITY_UPDATE, i++, EntityUpdatePacket.class, EntityUpdatePacket::new, ServerNetworkReceiver::onEntityUpdate);
        network.registerServerHandler(BLOCK_EDITOR_REQUEST, i++, BlockEditorRequestPacket.class, BlockEditorRequestPacket::new, ServerNetworkReceiver::onBlockEditorRequest);
        network.registerClientHandler(BLOCK_EDITOR_RESPONSE, i++, BlockEditorResponsePacket.class, BlockEditorResponsePacket::new, ClientNetworkReceiver::onBlockEditorResponse);
        network.registerServerHandler(ENTITY_EDITOR_REQUEST, i++, EntityEditorRequestPacket.class, EntityEditorRequestPacket::new, ServerNetworkReceiver::onEntityEditorRequest);
        network.registerClientHandler(ENTITY_EDITOR_RESPONSE, i++, EntityEditorResponsePacket.class, EntityEditorResponsePacket::new, ClientNetworkReceiver::onEntityEditorResponse);
        network.registerClientHandler(EDITOR_COMMAND, i++, EditorCommandPacket.class, EditorCommandPacket::new, ClientNetworkReceiver::onEditorCommand);
    }
}
