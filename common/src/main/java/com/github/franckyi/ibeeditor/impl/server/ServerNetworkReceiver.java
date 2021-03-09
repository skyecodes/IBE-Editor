package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.ibeeditor.impl.common.packet.*;
import com.github.franckyi.minecraft.api.common.world.Player;

public final class ServerNetworkReceiver {
    public static void clientModInstalled(NotifyServerPacket packet, Player sender) {
        IBEEditorServer.addAllowedPlayer(sender);
    }

    public static void updatePlayerMainHandItem(UpdatePlayerMainHandItemPacket packet, Player sender) {
        IBEEditorServer.updatePlayerMainHandItem(sender, packet.getItem());
    }

    public static void updatePlayerInventoryItem(UpdatePlayerInventoryItemPacket packet, Player sender) {
        IBEEditorServer.updatePlayerInventoryItem(sender, packet.getItem(), packet.getSlotId());
    }

    public static void updateBlockInventoryItem(UpdateBlockInventoryItemPacket packet, Player sender) {
        IBEEditorServer.updateBlockInventoryItem(sender, packet.getItem(), packet.getSlotId(), packet.getPos());
    }

    public static void updateBlock(UpdateBlockPacket packet, Player sender) {
        IBEEditorServer.updateBlock(sender, packet.getBlock(), packet.getPos());
    }

    public static void updateEntity(UpdateEntityPacket packet, Player sender) {
        IBEEditorServer.updateEntity(sender, packet.getEntity(), packet.getEntityId());
    }

    public static void requestOpenBlockEditor(OpenBlockEditorRequestPacket packet, Player sender) {
        ServerNetworkEmitter.openBlockEditorResponse(sender, packet, sender.getWorld().getBlock(packet.getPos()));
    }

    public static void requestOpenEntityEditor(OpenEntityEditorRequestPacket packet, Player sender) {
        ServerNetworkEmitter.openEntityEditorResponse(sender, packet, sender.getWorld().getEntity(packet.getEntityId()));
    }
}
