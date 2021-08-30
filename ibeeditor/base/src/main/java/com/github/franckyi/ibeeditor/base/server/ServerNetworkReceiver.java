package com.github.franckyi.ibeeditor.base.server;

import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.ibeeditor.base.common.NetworkManager;
import com.github.franckyi.ibeeditor.base.common.packet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerNetworkReceiver {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onClientNotification(ClientNotificationPacket packet, IPlayer sender) {
        log(NetworkManager.CLIENT_NOTIFICATION, sender);
        ServerContext.addModdedClient(sender);
    }

    public static void onPlayerMainHandItemUpdate(PlayerMainHandItemUpdatePacket packet, IPlayer sender) {
        log(NetworkManager.PLAYER_MAIN_HAND_ITEM_UPDATE, sender);
        ServerEditorLogic.updatePlayerMainHandItem(sender, packet.getItem());
    }

    public static void onPlayerInventoryItemUpdate(PlayerInventoryItemUpdatePacket packet, IPlayer sender) {
        log(NetworkManager.PLAYER_INVENTORY_ITEM_UPDATE, sender);
        ServerEditorLogic.updatePlayerInventoryItem(sender, packet.getItem(), packet.getSlotId());
    }

    public static void onBlockInventoryItemUpdate(BlockInventoryItemUpdatePacket packet, IPlayer sender) {
        log(NetworkManager.BLOCK_INVENTORY_ITEM_UPDATE, sender);
        ServerEditorLogic.updateBlockInventoryItem(sender, packet.getItem(), packet.getSlotId(), packet.getPos());
    }

    public static void onBlockUpdate(BlockUpdatePacket packet, IPlayer sender) {
        log(NetworkManager.BLOCK_UPDATE, sender);
        ServerEditorLogic.updateBlock(sender, packet.getBlock());
    }

    public static void onEntityUpdate(EntityUpdatePacket packet, IPlayer sender) {
        log(NetworkManager.ENTITY_UPDATE, sender);
        ServerEditorLogic.updateEntity(sender, packet.getEntity(), packet.getEntityId());
    }

    public static void onBlockEditorRequest(BlockEditorRequestPacket packet, IPlayer sender) {
        log(NetworkManager.BLOCK_EDITOR_REQUEST, sender);
        ServerNetworkEmitter.sendBlockEditorResponse(sender, packet, sender.getWorld().getBlockData(packet.getPos()));
    }

    public static void onEntityEditorRequest(EntityEditorRequestPacket packet, IPlayer sender) {
        log(NetworkManager.ENTITY_EDITOR_REQUEST, sender);
        ServerNetworkEmitter.sendEntityEditorResponse(sender, packet, sender.getWorld().getEntity(packet.getEntityId()));
    }

    private static void log(String id, IPlayer player) {
        LOGGER.debug("Receiving packet {} from player {}", id, player.getProfileName());
    }
}
