package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.ibeeditor.impl.client.ClientNetworkReceiver;
import com.github.franckyi.ibeeditor.impl.common.packet.*;
import com.github.franckyi.ibeeditor.impl.server.ServerNetworkReceiver;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.network.Network;

public final class IBEEditorNetwork {
    private static int i = 0;
    public static final String NOTIFY_CLIENT = "ibeeditor:network/notify_client";
    public static final String NOTIFY_SERVER = "ibeeditor:network/notify_server";

    public static final String UPDATE_PLAYER_MAIN_HAND_ITEM = "ibeeditor:network/update_player_main_hand_item";
    public static final String UPDATE_PLAYER_INVENTORY_ITEM = "ibeeditor:network/update_player_inventory_item";
    public static final String UPDATE_BLOCK_INVENTORY_ITEM = "ibeeditor:network/update_block_inventory_item";
    public static final String UPDATE_BLOCK = "ibeeditor:network/update_block";
    public static final String UPDATE_ENTITY = "ibeeditor:network/update_entity";

    public static final String OPEN_BLOCK_EDITOR_REQUEST = "ibeeditor:network/open_block_editor_request";
    public static final String OPEN_BLOCK_EDITOR_RESPONSE = "ibeeditor:network/open_block_editor_response";
    public static final String OPEN_ENTITY_EDITOR_REQUEST = "ibeeditor:network/open_entity_editor_request";
    public static final String OPEN_ENTITY_EDITOR_RESPONSE = "ibeeditor:network/open_entity_editor_response";

    public static final String TRIGGER_OPEN_EDITOR = "ibeeditor:network/trigger_open_editor";

    private static Network network() {
        return Minecraft.getCommon().getNetwork();
    }

    public static void init() {
        network().registerClientHandler(NOTIFY_CLIENT, i++, NotifyClientPacket.class, NotifyClientPacket::new, ClientNetworkReceiver::serverModInstalled);
        network().registerServerHandler(NOTIFY_SERVER, i++, NotifyServerPacket.class, NotifyServerPacket::new, ServerNetworkReceiver::clientModInstalled);
        network().registerServerHandler(UPDATE_PLAYER_MAIN_HAND_ITEM, i++, UpdatePlayerMainHandItemPacket.class, UpdatePlayerMainHandItemPacket::new, ServerNetworkReceiver::updatePlayerMainHandItem);
        network().registerServerHandler(UPDATE_PLAYER_INVENTORY_ITEM, i++, UpdatePlayerInventoryItemPacket.class, UpdatePlayerInventoryItemPacket::new, ServerNetworkReceiver::updatePlayerInventoryItem);
        network().registerServerHandler(UPDATE_BLOCK_INVENTORY_ITEM, i++, UpdateBlockInventoryItemPacket.class, UpdateBlockInventoryItemPacket::new, ServerNetworkReceiver::updateBlockInventoryItem);
        network().registerServerHandler(UPDATE_BLOCK, i++, UpdateBlockPacket.class, UpdateBlockPacket::new, ServerNetworkReceiver::updateBlock);
        network().registerServerHandler(UPDATE_ENTITY, i++, UpdateEntityPacket.class, UpdateEntityPacket::new, ServerNetworkReceiver::updateEntity);
        network().registerServerHandler(OPEN_BLOCK_EDITOR_REQUEST, i++, OpenBlockEditorRequestPacket.class, OpenBlockEditorRequestPacket::new, ServerNetworkReceiver::requestOpenBlockEditor);
        network().registerClientHandler(OPEN_BLOCK_EDITOR_RESPONSE, i++, OpenBlockEditorResponsePacket.class, OpenBlockEditorResponsePacket::new, ClientNetworkReceiver::openBlockEditor);
        network().registerServerHandler(OPEN_ENTITY_EDITOR_REQUEST, i++, OpenEntityEditorRequestPacket.class, OpenEntityEditorRequestPacket::new, ServerNetworkReceiver::requestOpenEntityEditor);
        network().registerClientHandler(OPEN_ENTITY_EDITOR_RESPONSE, i++, OpenEntityEditorResponsePacket.class, OpenEntityEditorResponsePacket::new, ClientNetworkReceiver::openEntityEditor);
        network().registerClientHandler(TRIGGER_OPEN_EDITOR, i++, TriggerOpenEditorPacket.class, TriggerOpenEditorPacket::new, ClientNetworkReceiver::openEditorTriggered);
    }
}
