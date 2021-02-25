package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.ibeeditor.impl.common.packet.*;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public final class ServerNetworkReceiver {
    public static void clientModInstalled(NotifyServerPacket packet, Player sender) {
        IBEEditorServer.addAllowedPlayer(sender);
    }

    public static void updatePlayerMainHandItem(UpdatePlayerMainHandItemPacket packet, Player sender) {
        sender.setItemMainHand(packet.getItem());
        sender.sendMessage(text("Item updated!", GREEN));
    }

    public static void updatePlayerInventoryItem(UpdatePlayerInventoryItemPacket packet, Player sender) {
        sender.setInventoryItem(packet.getSlotId(), packet.getItem());
        sender.sendMessage(text("Item updated!", GREEN));
    }

    public static void updateBlockInventoryItem(UpdateBlockInventoryItemPacket packet, Player sender) {
        sender.getWorld().setBlockInventoryItem(packet.getPos(), packet.getSlotId(), packet.getItem());
        sender.sendMessage(text("Item updated!", GREEN));
    }

    public static void updateBlock(UpdateBlockPacket packet, Player sender) {
        sender.getWorld().setBlockData(packet.getPos(), packet.getTag());
        sender.sendMessage(text("Block updated!", GREEN));
    }

    public static void updateEntity(UpdateEntityPacket packet, Player sender) {
        sender.getWorld().setEntityData(packet.getEntityId(), packet.getTag());
        sender.sendMessage(text("Entity updated!", GREEN));
    }

    public static void requestOpenBlockEditor(OpenBlockEditorRequestPacket packet, Player sender) {
        ServerNetworkEmitter.openBlockEditorResponse(sender, packet, sender.getWorld().getBlock(packet.getPos()));
    }

    public static void requestOpenEntityEditor(OpenEntityEditorRequestPacket packet, Player sender) {
        ServerNetworkEmitter.openEntityEditorResponse(sender, packet, sender.getWorld().getEntity(packet.getEntityId()));
    }
}
