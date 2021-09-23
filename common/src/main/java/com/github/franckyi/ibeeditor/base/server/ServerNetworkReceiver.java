package com.github.franckyi.ibeeditor.base.server;

import com.github.franckyi.ibeeditor.base.common.NetworkManager;
import com.github.franckyi.ibeeditor.base.common.packet.*;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerNetworkReceiver {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onClientNotification(ClientNotificationPacket packet, ServerPlayer sender) {
        log(NetworkManager.CLIENT_NOTIFICATION, sender);
        if (packet.getValue()) {
            ServerContext.addModdedClient(sender);
        }
    }

    public static void onPlayerMainHandItemUpdate(PlayerMainHandItemUpdatePacket packet, ServerPlayer sender) {
        log(NetworkManager.PLAYER_MAIN_HAND_ITEM_UPDATE, sender);
        ServerEditorLogic.updatePlayerMainHandItem(sender, packet.getItem());
    }

    public static void onPlayerInventoryItemUpdate(PlayerInventoryItemUpdatePacket packet, ServerPlayer sender) {
        log(NetworkManager.PLAYER_INVENTORY_ITEM_UPDATE, sender);
        ServerEditorLogic.updatePlayerInventoryItem(sender, packet.getItem(), packet.getSlotId());
    }

    public static void onBlockInventoryItemUpdate(BlockInventoryItemUpdatePacket packet, ServerPlayer sender) {
        log(NetworkManager.BLOCK_INVENTORY_ITEM_UPDATE, sender);
        ServerEditorLogic.updateBlockInventoryItem(sender, packet.getItem(), packet.getSlotId(), packet.getPos());
    }

    public static void onBlockUpdate(BlockUpdatePacket packet, ServerPlayer sender) {
        log(NetworkManager.BLOCK_UPDATE, sender);
        ServerEditorLogic.updateBlock(sender, packet.getPos(), packet.getState(), packet.getTag());
    }

    public static void onEntityUpdate(EntityUpdatePacket packet, ServerPlayer sender) {
        log(NetworkManager.ENTITY_UPDATE, sender);
        ServerEditorLogic.updateEntity(sender, packet.getEntity(), packet.getEntityId());
    }

    public static void onBlockEditorRequest(BlockEditorRequestPacket packet, ServerPlayer sender) {
        log(NetworkManager.BLOCK_EDITOR_REQUEST, sender);
        ServerEditorLogic.processBlockEditorRequest(sender, packet.getEditorType(), packet.getPos());
    }

    public static void onEntityEditorRequest(EntityEditorRequestPacket packet, ServerPlayer sender) {
        log(NetworkManager.ENTITY_EDITOR_REQUEST, sender);
        ServerEditorLogic.processEntityEditorRequest(sender, packet.getEditorType(), packet.getEntityId());
    }

    private static void log(String id, ServerPlayer player) {
        LOGGER.debug("Receiving packet {} from player {}", id, player.getGameProfile().getName());
    }
}
