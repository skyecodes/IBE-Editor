package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.BlockPos;
import com.github.franckyi.gameadapter.api.common.network.Packet;
import com.github.franckyi.gameadapter.api.common.world.Block;
import com.github.franckyi.gameadapter.api.common.world.Entity;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.Networking;
import com.github.franckyi.ibeeditor.base.common.packet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientNetworkEmitter {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void sendClientNotification() {
        send(Networking.CLIENT_NOTIFICATION, new ClientNotificationPacket());
    }

    public static void sendPlayerMainHandItemUpdate(Item item) {
        send(Networking.PLAYER_MAIN_HAND_ITEM_UPDATE, new PlayerMainHandItemUpdatePacket(item));
    }

    public static void sendPlayerInventoryItemUpdate(Item item, int slotId) {
        send(Networking.PLAYER_INVENTORY_ITEM_UPDATE, new PlayerInventoryItemUpdatePacket(item, slotId));
    }

    public static void sendBlockInventoryItemUpdate(Item item, int slotId, BlockPos blockPos) {
        send(Networking.BLOCK_INVENTORY_ITEM_UPDATE, new BlockInventoryItemUpdatePacket(item, slotId, blockPos));
    }

    public static void sendBlockUpdate(BlockPos blockPos, Block block) {
        send(Networking.BLOCK_UPDATE, new BlockUpdatePacket(blockPos, block));
    }

    public static void sendEntityUpdate(int entityId, Entity entity) {
        send(Networking.ENTITY_UPDATE, new EntityUpdatePacket(entityId, entity));
    }

    public static void sendBlockEditorRequest(BlockPos blockPos, EditorType type) {
        send(Networking.BLOCK_EDITOR_REQUEST, new BlockEditorRequestPacket(blockPos, type));
    }

    public static void sendEntityEditorRequest(int entityId, EditorType type) {
        send(Networking.ENTITY_EDITOR_REQUEST, new EntityEditorRequestPacket(entityId, type));
    }

    private static void send(String id, Packet packet) {
        LOGGER.debug("Sending packet {}", id);
        Game.getCommon().getNetwork().sendToServer(id, packet);
    }
}
