package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.world.IBlockPos;
import com.github.franckyi.gameadapter.api.common.world.WorldBlockData;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.ModNetwork;
import com.github.franckyi.ibeeditor.base.common.Packet;
import com.github.franckyi.ibeeditor.base.common.packet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientNetworkEmitter {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void sendClientNotification() {
        send(ModNetwork.CLIENT_NOTIFICATION, new ClientNotificationPacket());
    }

    public static void sendPlayerMainHandItemUpdate(IItemStack itemStack) {
        send(ModNetwork.PLAYER_MAIN_HAND_ITEM_UPDATE, new PlayerMainHandItemUpdatePacket(itemStack));
    }

    public static void sendPlayerInventoryItemUpdate(IItemStack itemStack, int slotId) {
        send(ModNetwork.PLAYER_INVENTORY_ITEM_UPDATE, new PlayerInventoryItemUpdatePacket(itemStack, slotId));
    }

    public static void sendBlockInventoryItemUpdate(IItemStack itemStack, int slotId, IBlockPos blockPos) {
        send(ModNetwork.BLOCK_INVENTORY_ITEM_UPDATE, new BlockInventoryItemUpdatePacket(itemStack, slotId, blockPos));
    }

    public static void sendBlockUpdate(WorldBlockData block) {
        send(ModNetwork.BLOCK_UPDATE, new BlockUpdatePacket(block));
    }

    public static void sendEntityUpdate(int entityId, ICompoundTag tag) {
        send(ModNetwork.ENTITY_UPDATE, new EntityUpdatePacket(entityId, tag));
    }

    public static void sendBlockEditorRequest(IBlockPos blockPos, EditorType type) {
        send(ModNetwork.BLOCK_EDITOR_REQUEST, new BlockEditorRequestPacket(blockPos, type));
    }

    public static void sendEntityEditorRequest(int entityId, EditorType type) {
        send(ModNetwork.ENTITY_EDITOR_REQUEST, new EntityEditorRequestPacket(entityId, type));
    }

    private static void send(String id, Packet packet) {
        LOGGER.debug("Sending packet {} to the server", id);
        ModNetwork.get().sendToServer(id, packet);
    }
}
