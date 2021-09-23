package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.NetworkManager;
import com.github.franckyi.ibeeditor.base.common.Packet;
import com.github.franckyi.ibeeditor.base.common.packet.*;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientNetworkEmitter {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void sendClientNotification() {
        send(NetworkManager.CLIENT_NOTIFICATION, new ClientNotificationPacket());
    }

    public static void sendPlayerMainHandItemUpdate(ItemStack itemStack) {
        send(NetworkManager.PLAYER_MAIN_HAND_ITEM_UPDATE, new PlayerMainHandItemUpdatePacket(itemStack));
    }

    public static void sendPlayerInventoryItemUpdate(ItemStack itemStack, int slotId) {
        send(NetworkManager.PLAYER_INVENTORY_ITEM_UPDATE, new PlayerInventoryItemUpdatePacket(itemStack, slotId));
    }

    public static void sendBlockInventoryItemUpdate(ItemStack itemStack, int slotId, BlockPos blockPos) {
        send(NetworkManager.BLOCK_INVENTORY_ITEM_UPDATE, new BlockInventoryItemUpdatePacket(itemStack, slotId, blockPos));
    }

    public static void sendBlockUpdate(BlockPos pos, BlockState state, CompoundTag tag) {
        send(NetworkManager.BLOCK_UPDATE, new BlockUpdatePacket(pos, state, tag));
    }

    public static void sendEntityUpdate(int entityId, CompoundTag tag) {
        send(NetworkManager.ENTITY_UPDATE, new EntityUpdatePacket(entityId, tag));
    }

    public static void sendBlockEditorRequest(BlockPos blockPos, EditorType type) {
        send(NetworkManager.BLOCK_EDITOR_REQUEST, new BlockEditorRequestPacket(type, blockPos));
    }

    public static void sendEntityEditorRequest(int entityId, EditorType type) {
        send(NetworkManager.ENTITY_EDITOR_REQUEST, new EntityEditorRequestPacket(type, entityId));
    }

    private static void send(String id, Packet packet) {
        LOGGER.debug("Sending packet {} to the server", id);
        NetworkManager.sendToServer(id, packet);
    }
}
