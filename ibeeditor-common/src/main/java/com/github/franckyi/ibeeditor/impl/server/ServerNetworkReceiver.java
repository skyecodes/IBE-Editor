package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.ibeeditor.impl.common.Networking;
import com.github.franckyi.ibeeditor.impl.common.packet.*;
import com.github.franckyi.minecraft.api.common.world.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerNetworkReceiver {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onClientNotification(ClientNotificationPacket packet, Player sender) {
        log(Networking.CLIENT_NOTIFICATION, sender);
        ServerContext.addModdedClient(sender);
    }

    public static void onPlayerMainHandItemUpdate(PlayerMainHandItemUpdatePacket packet, Player sender) {
        log(Networking.PLAYER_MAIN_HAND_ITEM_UPDATE, sender);
        ServerEditorLogic.updatePlayerMainHandItem(sender, packet.getItem());
    }

    public static void onPlayerInventoryItemUpdate(PlayerInventoryItemUpdatePacket packet, Player sender) {
        log(Networking.PLAYER_INVENTORY_ITEM_UPDATE, sender);
        ServerEditorLogic.updatePlayerInventoryItem(sender, packet.getItem(), packet.getSlotId());
    }

    public static void onBlockInventoryItemUpdate(BlockInventoryItemUpdatePacket packet, Player sender) {
        log(Networking.BLOCK_INVENTORY_ITEM_UPDATE, sender);
        ServerEditorLogic.updateBlockInventoryItem(sender, packet.getItem(), packet.getSlotId(), packet.getPos());
    }

    public static void onBlockUpdate(BlockUpdatePacket packet, Player sender) {
        log(Networking.BLOCK_UPDATE, sender);
        ServerEditorLogic.updateBlock(sender, packet.getBlock(), packet.getPos());
    }

    public static void onEntityUpdate(EntityUpdatePacket packet, Player sender) {
        log(Networking.ENTITY_UPDATE, sender);
        ServerEditorLogic.updateEntity(sender, packet.getEntity(), packet.getEntityId());
    }

    public static void onBlockEditorRequest(BlockEditorRequestPacket packet, Player sender) {
        log(Networking.BLOCK_EDITOR_REQUEST, sender);
        ServerNetworkEmitter.sendBlockEditorResponse(sender, packet, sender.getWorld().getBlock(packet.getPos()));
    }

    public static void onEntityEditorRequest(EntityEditorRequestPacket packet, Player sender) {
        log(Networking.ENTITY_EDITOR_REQUEST, sender);
        ServerNetworkEmitter.sendEntityEditorResponse(sender, packet, sender.getWorld().getEntity(packet.getEntityId()));
    }

    private static void log(String id, Player player) {
        LOGGER.debug("Receiving packet {} from player {}", id, player);
    }
}
