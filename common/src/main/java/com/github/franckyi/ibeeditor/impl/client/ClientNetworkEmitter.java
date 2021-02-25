package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.network.Packet;
import com.github.franckyi.ibeeditor.impl.common.IBEEditorNetwork;
import com.github.franckyi.ibeeditor.impl.common.packet.*;

public final class ClientNetworkEmitter {
    public static void notifyServer() {
        send(IBEEditorNetwork.NOTIFY_SERVER, new NotifyServerPacket());
    }

    public static void updatePlayerMainHandItem(Item item) {
        send(IBEEditorNetwork.UPDATE_PLAYER_MAIN_HAND_ITEM, new UpdatePlayerMainHandItemPacket(item));
    }

    public static void updatePlayerInventoryItem(Item item, int slotId) {
        send(IBEEditorNetwork.UPDATE_PLAYER_INVENTORY_ITEM, new UpdatePlayerInventoryItemPacket(item, slotId));
    }

    public static void updateBlockInventoryItem(Item item, int slotId, Pos blockPos) {
        send(IBEEditorNetwork.UPDATE_BLOCK_INVENTORY_ITEM, new UpdateBlockInventoryItemPacket(item, slotId, blockPos));
    }

    public static void updateBlock(Pos blockPos, Block block) {
        send(IBEEditorNetwork.UPDATE_BLOCK, new UpdateBlockPacket(blockPos, block));
    }

    public static void updateEntity(int entityId, Entity entity) {
        send(IBEEditorNetwork.UPDATE_ENTITY, new UpdateEntityPacket(entityId, entity));
    }

    public static void requestOpenBlockEditor(Pos blockPos, boolean nbt) {
        send(IBEEditorNetwork.OPEN_BLOCK_EDITOR_REQUEST, new OpenBlockEditorRequestPacket(blockPos, nbt));
    }

    public static void requestOpenEntityEditor(int entityId, boolean nbt) {
        send(IBEEditorNetwork.OPEN_ENTITY_EDITOR_REQUEST, new OpenEntityEditorRequestPacket(entityId, nbt));
    }

    private static void send(String id, Packet packet) {
        GameHooks.common().getNetwork().sendToServer(id, packet);
    }
}
