package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.ibeeditor.common.packet.*;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerNetworkEmitter {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void sendServerNotification(ServerPlayer player) {
        send(NetworkManager.SERVER_NOTIFICATION, player, new ServerNotificationPacket());
    }

    public static void sendMainHandItemEditorResponse(ServerPlayer player, EditorType editorType, ItemStack itemStack) {
        send(NetworkManager.MAIN_HAND_ITEM_EDITOR_RESPONSE, player, new MainHandItemEditorResponsePacket(editorType, itemStack));
    }

    public static void sendPlayerInventoryItemEditorResponse(ServerPlayer player, EditorType editorType, int slotIndex, boolean isCreativeInventoryScreen, ItemStack itemStack) {
        send(NetworkManager.PLAYER_INVENTORY_ITEM_EDITOR_RESPONSE, player, new PlayerInventoryItemEditorResponsePacket(editorType, slotIndex, isCreativeInventoryScreen, itemStack));
    }

    public static void sendBlockInventoryItemEditorResponse(ServerPlayer player, EditorType editorType, int slotIndex, BlockPos pos, ItemStack itemStack) {
        send(NetworkManager.BLOCK_INVENTORY_ITEM_EDITOR_RESPONSE, player, new BlockInventoryItemEditorResponsePacket(editorType, slotIndex, pos, itemStack));
    }

    public static void sendBlockEditorResponse(ServerPlayer player, EditorType editorType, BlockPos pos, BlockState state, CompoundTag tag) {
        send(NetworkManager.BLOCK_EDITOR_RESPONSE, player, new BlockEditorResponsePacket(editorType, pos, state, tag));
    }

    public static void sendEntityEditorResponse(ServerPlayer player, EditorType editorType, int entityId, CompoundTag tag) {
        send(NetworkManager.ENTITY_EDITOR_RESPONSE, player, new EntityEditorResponsePacket(editorType, entityId, tag));
    }

    public static void sendWorldEditorCommand(ServerPlayer player, EditorType editorType) {
        sendEditorCommand(player, EditorCommandPacket.TARGET_WORLD, editorType);
    }

    public static void sendItemEditorCommand(ServerPlayer player, EditorType editorType) {
        sendEditorCommand(player, EditorCommandPacket.TARGET_ITEM, editorType);
    }

    public static void sendBlockEditorCommand(ServerPlayer player, EditorType editorType) {
        sendEditorCommand(player, EditorCommandPacket.TARGET_BLOCK, editorType);
    }

    public static void sendEntityEditorCommand(ServerPlayer player, EditorType editorType) {
        sendEditorCommand(player, EditorCommandPacket.TARGET_ENTITY, editorType);
    }

    public static void sendSelfEditorCommand(ServerPlayer player, EditorType editorType) {
        sendEditorCommand(player, EditorCommandPacket.TARGET_SELF, editorType);
    }

    private static void sendEditorCommand(ServerPlayer player, byte target, EditorType editorType) {
        send(NetworkManager.EDITOR_COMMAND, player, new EditorCommandPacket(target, editorType));
    }

    private static void send(String id, ServerPlayer player, Packet packet) {
        LOGGER.debug("Sending packet {} to player {}", id, player.getGameProfile().getName());
        NetworkManager.sendToClient(id, player, packet);
    }
}
