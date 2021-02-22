package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.ibeeditor.impl.IBEEditorNetwork;
import com.github.franckyi.ibeeditor.impl.common.packet.UpdateBlockInventoryItemPacket;
import com.github.franckyi.ibeeditor.impl.common.packet.UpdatePlayerInventoryItemPacket;
import com.github.franckyi.ibeeditor.impl.common.packet.UpdatePlayerMainHandItemPacket;

public final class ClientNetwork {
    private static Network<?> network() {
        return GameHooks.common().network();
    }

    public static void updatePlayerMainHandItem(Item item) {
        network().sendToServer(IBEEditorNetwork.UPDATE_PLAYER_MAIN_HAND_ITEM, new UpdatePlayerMainHandItemPacket(item));
    }

    public static void updatePlayerInventoryItem(Item item, int slotId) {
        network().sendToServer(IBEEditorNetwork.UPDATE_PLAYER_INVENTORY_ITEM, new UpdatePlayerInventoryItemPacket(item, slotId));
    }

    public static void updateBlockInventoryItem(Item item, int slotId, Pos pos) {
        network().sendToServer(IBEEditorNetwork.UPDATE_BLOCK_INVENTORY_ITEM, new UpdateBlockInventoryItemPacket(item, slotId, pos));
    }
}
