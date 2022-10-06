package com.github.franckyi.ibeeditor.common.logic;

import com.github.franckyi.ibeeditor.common.CommonUtil;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.common.network.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;

public final class ServerEditorUpdateLogic {
    public static void onMainHandItemEditorUpdate(ServerPlayer player, MainHandItemEditorPacket.Update response) {
        if (!PermissionLogic.hasPermission(player)) {
            CommonUtil.showPermissionError(player, ModTexts.ITEM);
            return;
        }
        player.setItemInHand(InteractionHand.MAIN_HAND, response.getItemStack());
        CommonUtil.showUpdateSuccess(player, ModTexts.ITEM);
    }

    public static void onPlayerInventoryItemEditorUpdate(ServerPlayer player, PlayerInventoryItemEditorPacket.Update response) {
        if (!PermissionLogic.hasPermission(player)) {
            CommonUtil.showPermissionError(player, ModTexts.ITEM);
            return;
        }
        player.getInventory().setItem(response.getSlot(), response.getItemStack());
        CommonUtil.showUpdateSuccess(player, ModTexts.ITEM);
    }

    public static void onBlockInventoryItemEditorUpdate(ServerPlayer player, BlockInventoryItemEditorPacket.Update response) {
        if (!PermissionLogic.hasPermission(player)) {
            CommonUtil.showPermissionError(player, ModTexts.ITEM);
            return;
        }
        if (player.getLevel().getBlockEntity(response.getBlockPos()) instanceof Container container) {
            container.setItem(response.getSlot(), response.getItemStack());
            CommonUtil.showUpdateSuccess(player, ModTexts.ITEM);
        } else {
            CommonUtil.showTargetError(player, ModTexts.ITEM);
        }
    }

    public static void onEntityInventoryItemEditorUpdate(ServerPlayer player, EntityInventoryItemEditorPacket.Update response) {
        if (!PermissionLogic.hasPermission(player)) {
            CommonUtil.showPermissionError(player, ModTexts.ITEM);
            return;
        }
        if (player.getLevel().getEntity(response.getEntityId()) instanceof Container container) {
            container.setItem(response.getSlot(), response.getItemStack());
            CommonUtil.showUpdateSuccess(player, ModTexts.ITEM);
        } else {
            CommonUtil.showTargetError(player, ModTexts.ITEM);
        }
    }

    public static void onBlockEditorUpdate(ServerPlayer player, BlockEditorPacket.Update update) {
        if (!PermissionLogic.hasPermission(player)) {
            CommonUtil.showPermissionError(player, ModTexts.BLOCK);
            return;
        }
        var level = player.getLevel();
        level.setBlockAndUpdate(update.getBlockPos(), update.getBlockState());
        var blockEntity = level.getBlockEntity(update.getBlockPos());
        if (update.getTag() != null) {
            if (blockEntity == null) {
                CommonUtil.showTargetError(player, ModTexts.BLOCK);
                return;
            }
            blockEntity.load(update.getTag());
        }
        CommonUtil.showUpdateSuccess(player, ModTexts.BLOCK);
    }

    public static void onEntityEditorUpdate(ServerPlayer player, EntityEditorPacket.Update update) {
        if (!PermissionLogic.hasPermission(player)) {
            CommonUtil.showPermissionError(player, ModTexts.ENTITY);
            return;
        }
        var entity = player.getLevel().getEntity(update.getEntityId());
        if (entity != null) {
            entity.load(update.getTag());
            CommonUtil.showUpdateSuccess(player, ModTexts.ENTITY);
        } else {
            CommonUtil.showTargetError(player, ModTexts.ENTITY);
        }
    }

}
