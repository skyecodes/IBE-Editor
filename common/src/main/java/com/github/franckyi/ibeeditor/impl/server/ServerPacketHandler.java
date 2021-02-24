package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.gamehooks.api.common.ServerPlayer;
import com.github.franckyi.ibeeditor.impl.common.packet.*;

public final class ServerPacketHandler {
    public static void clientModInstalled(NotifyServerPacket packet, ServerPlayer sender) {
        IBEEditorServer.addAllowedPlayer(sender);
    }

    public static void updatePlayerMainHandItem(UpdatePlayerMainHandItemPacket packet, ServerPlayer sender) {
        sender.setItemMainHand(packet.getItem());
    }

    public static void updatePlayerInventoryItem(UpdatePlayerInventoryItemPacket packet, ServerPlayer sender) {
        sender.setInventoryItem(packet.getSlotId(), packet.getItem());
    }

    public static void updateBlockInventoryItem(UpdateBlockInventoryItemPacket packet, ServerPlayer sender) {
        sender.getWorld().setBlockInventoryItem(packet.getPos(), packet.getSlotId(), packet.getItem());
    }

    public static void updateBlock(UpdateBlockPacket packet, ServerPlayer sender) {
        sender.getWorld().setBlockData(packet.getPos(), packet.getTag());
    }

    public static void updateEntity(UpdateEntityPacket packet, ServerPlayer sender) {
        sender.getWorld().setEntityData(packet.getEntityId(), packet.getTag());
    }

    public static void requestOpenBlockEditor(OpenBlockEditorRequestPacket packet, ServerPlayer sender) {
        ServerNetwork.openBlockEditorResponse(sender, packet, sender.getWorld().getBlock(packet.getPos()));
    }

    public static void requestOpenEntityEditor(OpenEntityEditorRequestPacket packet, ServerPlayer sender) {
        ServerNetwork.openEntityEditorResponse(sender, packet, sender.getWorld().getEntity(packet.getEntityId()));
    }
}
