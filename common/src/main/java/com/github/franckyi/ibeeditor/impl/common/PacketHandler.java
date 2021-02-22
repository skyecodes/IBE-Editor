package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.ServerPlayer;
import com.github.franckyi.gamehooks.util.common.text.Text;
import com.github.franckyi.gamehooks.util.common.text.TextFormatting;
import com.github.franckyi.ibeeditor.impl.IBEEditorNetwork;
import com.github.franckyi.ibeeditor.impl.client.IBEEditorClient;
import com.github.franckyi.ibeeditor.impl.common.packet.*;

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

    public static void updateBlock(UpdateBlockPacket packet, ServerPlayer sender) {
        sender.getWorld().setBlockData(packet.getPos(), packet.getTag());
    }

    public static void updateEntity(UpdateEntityPacket packet, ServerPlayer sender) {
        sender.getWorld().setEntityData(packet.getEntityId(), packet.getTag());
    }

    public static void requestOpenBlockEditor(OpenBlockEditorRequestPacket packet, ServerPlayer sender) {
        Block block = sender.getWorld().getBlock(packet.getPos());
        GameHooks.common().network().sendToClient(IBEEditorNetwork.OPEN_BLOCK_EDITOR_RESPONSE, sender.getServerEntity(), new OpenBlockEditorResponsePacket(packet, block));
    }

    public static void openBlockEditor(OpenBlockEditorResponsePacket packet) {
        if (packet.getBlock().getTag() != null) {
            IBEEditorClient.openBlockEditor(packet.getBlock(), packet.getPos(), packet.isNBT());
        } else {
            GameHooks.client().player().sendMessage(Text.literal("No Block Entity found", TextFormatting.RED), false);
        }
    }
}
