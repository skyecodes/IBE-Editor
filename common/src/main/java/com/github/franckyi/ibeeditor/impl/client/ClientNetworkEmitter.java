package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.ibeeditor.impl.common.IBEEditorNetwork;
import com.github.franckyi.ibeeditor.impl.common.packet.*;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.network.Packet;
import com.github.franckyi.minecraft.api.common.world.Block;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.world.Item;

public final class ClientNetworkEmitter {
    public static void notifyServer() {
        send(IBEEditorNetwork.NOTIFY_SERVER, new NotifyServerPacket());
    }

    public static void updatePlayerMainHandItem(Item item) {
        send(IBEEditorNetwork.UPDATE_PLAYER_MAIN_HAND_ITEM, new UpdatePlayerMainHandItemPacket(item));
    }

    public static void updatePlayerInventoryItem(Item item, int slotId) {
        send(IBEEditorNetwork.UPDATE_PLAYER_INVENTORY_ITEM, new UpdatePlayerInventoryItemPacket(item, slotId));
    }

    public static void updateBlockInventoryItem(Item item, int slotId, BlockPos blockPos) {
        send(IBEEditorNetwork.UPDATE_BLOCK_INVENTORY_ITEM, new UpdateBlockInventoryItemPacket(item, slotId, blockPos));
    }

    public static void updateBlock(BlockPos blockPos, Block block) {
        send(IBEEditorNetwork.UPDATE_BLOCK, new UpdateBlockPacket(blockPos, block));
    }

    public static void updateEntity(int entityId, Entity entity) {
        send(IBEEditorNetwork.UPDATE_ENTITY, new UpdateEntityPacket(entityId, entity));
    }

    public static void requestOpenBlockEditor(BlockPos blockPos, boolean nbt) {
        send(IBEEditorNetwork.OPEN_BLOCK_EDITOR_REQUEST, new OpenBlockEditorRequestPacket(blockPos, nbt));
    }

    public static void requestOpenEntityEditor(int entityId, boolean nbt) {
        send(IBEEditorNetwork.OPEN_ENTITY_EDITOR_REQUEST, new OpenEntityEditorRequestPacket(entityId, nbt));
    }

    private static void send(String id, Packet packet) {
        Minecraft.getCommon().getNetwork().sendToServer(id, packet);
    }
}
