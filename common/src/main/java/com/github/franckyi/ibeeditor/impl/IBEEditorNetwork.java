package com.github.franckyi.ibeeditor.impl;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.ibeeditor.impl.common.PacketHandler;
import com.github.franckyi.ibeeditor.impl.common.packet.*;

public final class IBEEditorNetwork {
    public static final String UPDATE_PLAYER_MAIN_HAND_ITEM = "ibeeditor:network/update_player_main_hand_item";
    public static final String UPDATE_PLAYER_INVENTORY_ITEM = "ibeeditor:network/update_player_inventory_item";
    public static final String UPDATE_BLOCK_INVENTORY_ITEM = "ibeeditor:network/update_block_inventory_item";
    public static final String UPDATE_BLOCK = "ibeeditor:network/update_block";
    public static final String UPDATE_ENTITY = "ibeeditor:network/update_entity";
    public static final String OPEN_BLOCK_EDITOR_REQUEST = "ibeeditor:network/open_block_editor_request";
    public static final String OPEN_BLOCK_EDITOR_RESPONSE = "ibeeditor:network/open_block_editor_response";

    public static Network<?> network() {
        return GameHooks.common().network();
    }

    public static void init() {
        int i = 0;
        network().registerServerHandler(UPDATE_PLAYER_MAIN_HAND_ITEM, i++, UpdatePlayerMainHandItemPacket.class, UpdatePlayerMainHandItemPacket::new, PacketHandler::updatePlayerMainHandItem);
        network().registerServerHandler(UPDATE_PLAYER_INVENTORY_ITEM, i++, UpdatePlayerInventoryItemPacket.class, UpdatePlayerInventoryItemPacket::new, PacketHandler::updatePlayerInventoryItem);
        network().registerServerHandler(UPDATE_BLOCK_INVENTORY_ITEM, i++, UpdateBlockInventoryItemPacket.class, UpdateBlockInventoryItemPacket::new, PacketHandler::updateBlockInventoryItem);
        network().registerServerHandler(UPDATE_BLOCK, i++, UpdateBlockPacket.class, UpdateBlockPacket::new, PacketHandler::updateBlock);
        network().registerServerHandler(UPDATE_ENTITY, i++, UpdateEntityPacket.class, UpdateEntityPacket::new, PacketHandler::updateEntity);
        network().registerServerHandler(OPEN_BLOCK_EDITOR_REQUEST, i++, OpenBlockEditorRequestPacket.class, OpenBlockEditorRequestPacket::new, PacketHandler::requestOpenBlockEditor);
        network().registerClientHandler(OPEN_BLOCK_EDITOR_RESPONSE, i++, OpenBlockEditorResponsePacket.class, OpenBlockEditorResponsePacket::new, PacketHandler::openBlockEditor);
    }
}
