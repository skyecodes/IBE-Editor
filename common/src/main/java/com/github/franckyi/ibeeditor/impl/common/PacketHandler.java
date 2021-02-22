package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.gamehooks.api.common.ServerPlayer;
import com.github.franckyi.ibeeditor.impl.common.packet.UpdateBlockInventoryItemPacket;
import com.github.franckyi.ibeeditor.impl.common.packet.UpdatePlayerInventoryItemPacket;
import com.github.franckyi.ibeeditor.impl.common.packet.UpdatePlayerMainHandItemPacket;

public final class PacketHandler {
    public static void updatePlayerMainHandItem(UpdatePlayerMainHandItemPacket packet, ServerPlayer sender) {
        sender.setItemMainHand(packet.getItem());
    }

    public static void updatePlayerInventoryItem(UpdatePlayerInventoryItemPacket packet, ServerPlayer sender) {
        sender.setInventoryItem(packet.getSlotId(), packet.getItem());
    }

    public static void updateBlockInventoryItem(UpdateBlockInventoryItemPacket packet, ServerPlayer sender) {
        sender.getWorld().setBlockInventoryItem(packet.getPos(), packet.getSlotId(), packet.getItem());
    }
}
