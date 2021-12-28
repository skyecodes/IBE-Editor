package com.github.franckyi.ibeeditor.base.server;

import com.github.franckyi.ibeeditor.base.common.NetworkManager;
import com.github.franckyi.ibeeditor.base.common.Packet;
import com.github.franckyi.ibeeditor.base.common.packet.*;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerNetworkReceiver {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onClientNotification(ClientNotificationPacket packet, ServerPlayer player) {
        log(NetworkManager.CLIENT_NOTIFICATION, player);
        if (packet.getValue()) {
            ServerContext.addModdedClient(player);
        }
    }

    public static void onPlayerMainHandItemUpdate(PlayerMainHandItemUpdatePacket packet, ServerPlayer player) {
        log(NetworkManager.PLAYER_MAIN_HAND_ITEM_UPDATE, player);
        ServerEditorLogic.updatePlayerMainHandItem(player, packet.getItem());
    }

    public static void onPlayerInventoryItemUpdate(PlayerInventoryItemUpdatePacket packet, ServerPlayer player) {
        log(NetworkManager.PLAYER_INVENTORY_ITEM_UPDATE, player);
        ServerEditorLogic.updatePlayerInventoryItem(player, packet.getItem(), packet.getSlotId());
    }

    public static void onBlockInventoryItemUpdate(BlockInventoryItemUpdatePacket packet, ServerPlayer player) {
        log(NetworkManager.BLOCK_INVENTORY_ITEM_UPDATE, player);
        ServerEditorLogic.updateBlockInventoryItem(player, packet.getItem(), packet.getSlotId(), packet.getPos());
    }

    public static void onBlockUpdate(BlockUpdatePacket packet, ServerPlayer player) {
        log(NetworkManager.BLOCK_UPDATE, player);
        ServerEditorLogic.updateBlock(player, packet.getPos(), packet.getState(), packet.getTag());
    }

    public static void onEntityUpdate(EntityUpdatePacket packet, ServerPlayer player) {
        log(NetworkManager.ENTITY_UPDATE, player);
        ServerEditorLogic.updateEntity(player, packet.getEntity(), packet.getEntityId());
    }

    public static void onMainHandItemEditorRequest(MainHandItemEditorRequestPacket packet, ServerPlayer player) {
        log(NetworkManager.MAIN_HAND_ITEM_EDITOR_REQUEST, player);
        ServerEditorLogic.processMainHandItemEditorRequest(player, packet.getEditorType());
    }

    public static void onPlayerInventoryItemEditorRequest(PlayerInventoryItemEditorRequestPacket packet, ServerPlayer player) {
        log(NetworkManager.PLAYER_INVENTORY_ITEM_EDITOR_REQUEST, player);
        ServerEditorLogic.processPlayerInventoryItemEditorRequest(player, packet.getEditorType(), packet.getSlotIndex(), packet.isCreativeInventoryScreen());
    }

    public static void onBlockInventoryItemEditorRequest(BlockInventoryItemEditorRequestPacket packet, ServerPlayer player) {
        log(NetworkManager.BLOCK_INVENTORY_ITEM_EDITOR_REQUEST, player);
        ServerEditorLogic.processBlockInventoryItemEditorRequest(player, packet.getEditorType(), packet.getSlotIndex(), packet.getPos());
    }

    public static void onBlockEditorRequest(BlockEditorRequestPacket packet, ServerPlayer player) {
        log(NetworkManager.BLOCK_EDITOR_REQUEST, player);
        ServerEditorLogic.processBlockEditorRequest(player, packet.getEditorType(), packet.getPos());
    }

    public static void onEntityEditorRequest(EntityEditorRequestPacket packet, ServerPlayer player) {
        log(NetworkManager.ENTITY_EDITOR_REQUEST, player);
        ServerEditorLogic.processEntityEditorRequest(player, packet.getEditorType(), packet.getEntityId());
    }

    private static void log(String id, ServerPlayer player) {
        LOGGER.debug("Receiving packet {} from player {}", id, player.getGameProfile().getName());
    }
}
