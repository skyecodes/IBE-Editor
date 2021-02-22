package com.github.franckyi.ibeeditor.impl;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.ibeeditor.impl.common.PacketHandler;
import com.github.franckyi.ibeeditor.impl.common.packet.UpdateBlockInventoryItemPacket;
import com.github.franckyi.ibeeditor.impl.common.packet.UpdatePlayerInventoryItemPacket;
import com.github.franckyi.ibeeditor.impl.common.packet.UpdatePlayerMainHandItemPacket;

public final class IBEEditorNetwork {
    public static final String UPDATE_PLAYER_MAIN_HAND_ITEM = "ibeeditor:network/update_player_main_hand_item";
    public static final String UPDATE_PLAYER_INVENTORY_ITEM = "ibeeditor:network/update_player_inventory_item";
    public static final String UPDATE_BLOCK_INVENTORY_ITEM = "ibeeditor:network/update_block_inventory_item";

    public static Network<?> network() {
        return GameHooks.common().network();
    }

    public static void init() {
        int i = 0;
        network().registerServerHandler(UPDATE_PLAYER_MAIN_HAND_ITEM, i++, UpdatePlayerMainHandItemPacket.class, UpdatePlayerMainHandItemPacket::new, PacketHandler::updatePlayerMainHandItem);
        network().registerServerHandler(UPDATE_PLAYER_INVENTORY_ITEM, i++, UpdatePlayerInventoryItemPacket.class, UpdatePlayerInventoryItemPacket::new, PacketHandler::updatePlayerInventoryItem);
        network().registerServerHandler(UPDATE_BLOCK_INVENTORY_ITEM, i++, UpdateBlockInventoryItemPacket.class, UpdateBlockInventoryItemPacket::new, PacketHandler::updateBlockInventoryItem);
    }
}
