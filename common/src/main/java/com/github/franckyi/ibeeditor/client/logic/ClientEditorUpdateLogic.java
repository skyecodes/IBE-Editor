package com.github.franckyi.ibeeditor.client.logic;

import com.github.franckyi.ibeeditor.client.context.BlockEditorContext;
import com.github.franckyi.ibeeditor.client.context.EntityEditorContext;
import com.github.franckyi.ibeeditor.client.context.ItemEditorContext;
import com.github.franckyi.ibeeditor.common.network.*;

public class ClientEditorUpdateLogic {
    public static void updateMainHandItem(MainHandItemEditorPacket.Response response, ItemEditorContext context) {
        NetworkManager.sendToServer(NetworkManager.MAIN_HAND_ITEM_EDITOR_UPDATE, new MainHandItemEditorPacket.Update(response, context.getItemStack()));
    }

    public static void updatePlayerInventoryItem(PlayerInventoryItemEditorPacket.Response response, ItemEditorContext context) {
        NetworkManager.sendToServer(NetworkManager.PLAYER_INVENTORY_ITEM_EDITOR_UPDATE, new PlayerInventoryItemEditorPacket.Update(response, context.getItemStack()));
    }

    public static void updateBlockInventoryItem(BlockInventoryItemEditorPacket.Response response, ItemEditorContext context) {
        NetworkManager.sendToServer(NetworkManager.BLOCK_INVENTORY_ITEM_EDITOR_UPDATE, new BlockInventoryItemEditorPacket.Update(response, context.getItemStack()));
    }

    public static void updateBlock(BlockEditorPacket.Response response, BlockEditorContext context) {
        NetworkManager.sendToServer(NetworkManager.BLOCK_EDITOR_UPDATE, new BlockEditorPacket.Update(response, context.getBlockState(), context.getTag()));
    }

    public static void updateEntity(EntityEditorPacket.Response response, EntityEditorContext context) {
        NetworkManager.sendToServer(NetworkManager.ENTITY_EDITOR_UPDATE, new EntityEditorPacket.Update(response, context.getTag()));
    }
}
