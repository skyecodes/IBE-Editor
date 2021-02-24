package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.ibeeditor.impl.client.ClientPacketHandler;
import com.github.franckyi.ibeeditor.impl.common.packet.*;
import com.github.franckyi.ibeeditor.impl.server.ServerPacketHandler;

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

    public static Network<?> network() {
        return GameHooks.common().network();
    }

    public static void initCommon() {
        network().registerServerHandler(NOTIFY_SERVER, i++, NotifyServerPacket.class, NotifyServerPacket::new, ServerPacketHandler::clientModInstalled);
        network().registerServerHandler(UPDATE_PLAYER_MAIN_HAND_ITEM, i++, UpdatePlayerMainHandItemPacket.class, UpdatePlayerMainHandItemPacket::new, ServerPacketHandler::updatePlayerMainHandItem);
        network().registerServerHandler(UPDATE_PLAYER_INVENTORY_ITEM, i++, UpdatePlayerInventoryItemPacket.class, UpdatePlayerInventoryItemPacket::new, ServerPacketHandler::updatePlayerInventoryItem);
        network().registerServerHandler(UPDATE_BLOCK_INVENTORY_ITEM, i++, UpdateBlockInventoryItemPacket.class, UpdateBlockInventoryItemPacket::new, ServerPacketHandler::updateBlockInventoryItem);
        network().registerServerHandler(UPDATE_BLOCK, i++, UpdateBlockPacket.class, UpdateBlockPacket::new, ServerPacketHandler::updateBlock);
        network().registerServerHandler(UPDATE_ENTITY, i++, UpdateEntityPacket.class, UpdateEntityPacket::new, ServerPacketHandler::updateEntity);
        network().registerServerHandler(OPEN_BLOCK_EDITOR_REQUEST, i++, OpenBlockEditorRequestPacket.class, OpenBlockEditorRequestPacket::new, ServerPacketHandler::requestOpenBlockEditor);
        network().registerServerHandler(OPEN_ENTITY_EDITOR_REQUEST, i++, OpenEntityEditorRequestPacket.class, OpenEntityEditorRequestPacket::new, ServerPacketHandler::requestOpenEntityEditor);
    }

    public static void initClient() {
        network().registerClientHandler(NOTIFY_CLIENT, i++, NotifyClientPacket.class, NotifyClientPacket::new, ClientPacketHandler::serverModInstalled);
        network().registerClientHandler(OPEN_BLOCK_EDITOR_RESPONSE, i++, OpenBlockEditorResponsePacket.class, OpenBlockEditorResponsePacket::new, ClientPacketHandler::openBlockEditor);
        network().registerClientHandler(OPEN_ENTITY_EDITOR_RESPONSE, i++, OpenEntityEditorResponsePacket.class, OpenEntityEditorResponsePacket::new, ClientPacketHandler::openEntityEditor);
        network().registerClientHandler(TRIGGER_OPEN_EDITOR, i++, TriggerOpenEditorPacket.class, TriggerOpenEditorPacket::new, ClientPacketHandler::openEditorTriggered);
    }
}
