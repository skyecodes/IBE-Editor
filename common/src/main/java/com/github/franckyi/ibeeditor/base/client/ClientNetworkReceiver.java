package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.ibeeditor.base.common.ModTexts;
import com.github.franckyi.ibeeditor.base.common.NetworkManager;
import com.github.franckyi.ibeeditor.base.common.packet.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientNetworkReceiver {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onMainHandItemEditorResponse(MainHandItemEditorResponsePacket packet) {
        log(NetworkManager.MAIN_HAND_ITEM_EDITOR_RESPONSE);
        ClientEditorLogic.openMainHandItemEditor(packet.getItemStack(), packet.getEditorType());
    }

    public static void onPlayerInventoryItemEditorResponse(PlayerInventoryItemEditorResponsePacket packet) {
        log(NetworkManager.PLAYER_INVENTORY_ITEM_EDITOR_RESPONSE);
        ClientEditorLogic.openPlayerInventoryItemEditor(packet.getItemStack(), packet.getEditorType(), packet.getSlotIndex(), packet.isCreativeInventoryScreen());
    }

    public static void onBlockInventoryItemEditorResponse(BlockInventoryItemEditorResponsePacket packet) {
        log(NetworkManager.BLOCK_INVENTORY_ITEM_EDITOR_RESPONSE);
        ClientEditorLogic.openBlockInventoryItemEditor(packet.getItemStack(), packet.getEditorType(), packet.getSlotIndex(), packet.getPos());
    }

    public static void onBlockEditorResponse(BlockEditorResponsePacket packet) {
        log(NetworkManager.BLOCK_EDITOR_RESPONSE);
        ClientEditorLogic.openBlockEditor(packet.getPos(), packet.getState(), packet.getTag(), packet.getEditorType());
    }

    public static void onEntityEditorResponse(EntityEditorResponsePacket packet) {
        log(NetworkManager.ENTITY_EDITOR_RESPONSE);
        ClientEditorLogic.openEntityEditor(packet.getTag(), packet.getEntityId(), packet.getEditorType());
    }

    public static void onServerNotification(ServerNotificationPacket packet) {
        log(NetworkManager.SERVER_NOTIFICATION);
        ClientContext.setModInstalledOnServer(packet.getValue());
        ClientNetworkEmitter.sendClientNotification();
    }

    public static void onEditorCommand(EditorCommandPacket packet) {
        log(NetworkManager.EDITOR_COMMAND);
        switch (packet.getTarget()) {
            case EditorCommandPacket.TARGET_WORLD:
                ClientEditorLogic.openWorldEditor(packet.getType());
                break;
            case EditorCommandPacket.TARGET_ITEM:
                if (!ClientEditorLogic.tryOpenItemEditor(packet.getType())) {
                    sendMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ITEM));
                }
                break;
            case EditorCommandPacket.TARGET_BLOCK:
                if (!ClientEditorLogic.tryOpenBlockEditor(packet.getType())) {
                    sendMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.BLOCK));
                }
                break;
            case EditorCommandPacket.TARGET_ENTITY:
                if (!ClientEditorLogic.tryOpenEntityEditor(packet.getType())) {
                    sendMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ENTITY));
                }
                break;
            case EditorCommandPacket.TARGET_SELF:
                ClientEditorLogic.tryOpenSelfEditor(packet.getType());
                break;
        }
    }

    private static void sendMessage(Component message) {
        Minecraft.getInstance().player.displayClientMessage(message, false);
    }

    private static void log(String id) {
        LOGGER.debug("Receiving packet {}", id);
    }
}
