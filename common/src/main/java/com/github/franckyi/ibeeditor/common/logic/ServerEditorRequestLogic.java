package com.github.franckyi.ibeeditor.common.logic;

import com.github.franckyi.ibeeditor.common.CommonUtil;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.common.network.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;

public final class ServerEditorRequestLogic {
    public static void onMainHandItemEditorRequest(ServerPlayer player, MainHandItemEditorPacket.Request request) {
        NetworkManager.sendToClient(player, NetworkManager.MAIN_HAND_ITEM_EDITOR_RESPONSE, new MainHandItemEditorPacket.Response(request, PermissionLogic.hasPermission(player), player.getMainHandItem()));
    }

    public static void onPlayerInventoryItemEditorRequest(ServerPlayer player, PlayerInventoryItemEditorPacket.Request request) {
        NetworkManager.sendToClient(player, NetworkManager.PLAYER_INVENTORY_ITEM_EDITOR_RESPONSE, new PlayerInventoryItemEditorPacket.Response(request, PermissionLogic.hasPermission(player), player.getInventory().getItem(request.getSlot())));
    }

    public static void onBlockInventoryItemEditorRequest(ServerPlayer player, BlockInventoryItemEditorPacket.Request request) {
        if (player.getLevel().getBlockEntity(request.getBlockPos()) instanceof Container container) {
            NetworkManager.sendToClient(player, NetworkManager.BLOCK_INVENTORY_ITEM_EDITOR_RESPONSE, new BlockInventoryItemEditorPacket.Response(request, PermissionLogic.hasPermission(player), container.getItem(request.getSlot())));
        } else {
            CommonUtil.showTargetError(player, ModTexts.ITEM);
        }
    }

    public static void onBlockEditorRequest(ServerPlayer player, BlockEditorPacket.Request request) {
        var level = player.getLevel();
        var blockState = level.getBlockState(request.getBlockPos());
        var blockEntity = level.getBlockEntity(request.getBlockPos());
        var tag = blockEntity == null ? null : blockEntity.saveWithId();
        NetworkManager.sendToClient(player, NetworkManager.BLOCK_EDITOR_RESPONSE, new BlockEditorPacket.Response(request, PermissionLogic.hasPermission(player), blockState, tag));
    }

    public static void onEntityEditorRequest(ServerPlayer player, EntityEditorPacket.Request request) {
        if (PermissionLogic.hasPermission(player)) return;
    }
}
