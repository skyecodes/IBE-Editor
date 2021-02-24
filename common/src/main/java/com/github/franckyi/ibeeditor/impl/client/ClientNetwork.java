package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import com.github.franckyi.ibeeditor.impl.common.IBEEditorNetwork;
import com.github.franckyi.ibeeditor.impl.common.packet.*;

public final class ClientNetwork {
    private static Network<?> network() {
        return GameHooks.common().network();
    }

    public static void notifyServer() {
        network().sendToServer(IBEEditorNetwork.NOTIFY_SERVER, new NotifyServerPacket());
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

    public static void updateBlock(Pos pos, ObjectTag tag) {
        network().sendToServer(IBEEditorNetwork.UPDATE_BLOCK, new UpdateBlockPacket(pos, tag));
    }

    public static void updateEntity(int entityId, ObjectTag tag) {
        network().sendToServer(IBEEditorNetwork.UPDATE_ENTITY, new UpdateEntityPacket(entityId, tag));
    }

    public static void requestOpenBlockEditor(Pos pos, boolean nbt) {
        network().sendToServer(IBEEditorNetwork.OPEN_BLOCK_EDITOR_REQUEST, new OpenBlockEditorRequestPacket(pos, nbt));
    }

    public static void requestOpenEntityEditor(int entityId, boolean nbt) {
        network().sendToServer(IBEEditorNetwork.OPEN_ENTITY_EDITOR_REQUEST, new OpenEntityEditorRequestPacket(entityId, nbt));
    }
}
