package com.github.franckyi.ibeeditor.base.server;

import com.github.franckyi.ibeeditor.base.common.CommonConfiguration;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerEditorLogic {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void updatePlayerMainHandItem(ServerPlayer player, ItemStack itemStack) {
        if (checkPermission(player)) return;
        LOGGER.debug("Updating {}'s main hand item to {}", player.getGameProfile().getName(), itemStack);
        LOGGER.debug(itemStack.save(new CompoundTag()));
        player.setItemInHand(InteractionHand.MAIN_HAND, itemStack);
        player.displayClientMessage(ModTexts.Messages.successUpdate(ModTexts.ITEM), false);
    }

    public static void updatePlayerInventoryItem(ServerPlayer player, ItemStack itemStack, int slotId) {
        if (checkPermission(player)) return;
        LOGGER.debug("Updating {}'s inventory item at slot {} to {}", player.getGameProfile().getName(), slotId, itemStack);
        LOGGER.debug(itemStack.save(new CompoundTag()));
        player.getInventory().setItem(slotId, itemStack);
        player.displayClientMessage(ModTexts.Messages.successUpdate(ModTexts.ITEM), false);
    }

    public static void updateBlockInventoryItem(ServerPlayer player, ItemStack itemStack, int slotId, BlockPos blockPos) {
        if (checkPermission(player)) return;
        LOGGER.debug("Updating block inventory item at pos {} and slot {} to {}", blockPos, slotId, itemStack);
        LOGGER.debug(itemStack.save(new CompoundTag()));
        ServerLevel level = player.getLevel();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof Container) {
            ((Container) blockEntity).setItem(slotId, itemStack);
            player.displayClientMessage(ModTexts.Messages.successUpdate(ModTexts.ITEM), false);
        } else {
            player.displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ITEM), false);
        }
    }

    public static void updateBlock(ServerPlayer player, BlockPos pos, BlockState state, CompoundTag tag) {
        if (checkPermission(player)) return;
        LOGGER.debug("Updating block {} at pos {}", state, pos);
        LOGGER.debug(tag);
        ServerLevel level = player.getLevel();
        level.setBlockAndUpdate(pos, state);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity != null) {
            blockEntity.load(tag);
        }
        player.displayClientMessage(ModTexts.Messages.successUpdate(ModTexts.BLOCK), false);
    }

    public static void updateEntity(ServerPlayer player, CompoundTag tag, int entityId) {
        if (checkPermission(player)) return;
        LOGGER.debug("Updating entity {} with id {}", tag, entityId);
        LOGGER.debug(tag);
        Entity entity = player.getLevel().getEntity(entityId);
        if (entity != null) {
            entity.load(tag);
            player.displayClientMessage(ModTexts.Messages.successUpdate(ModTexts.ENTITY), false);
        } else {
            player.displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ENTITY), false);
        }
    }

    public static void processMainHandItemEditorRequest(ServerPlayer player, EditorType editorType) {
        if (checkPermission(player)) return;
        ServerNetworkEmitter.sendMainHandItemEditorResponse(player, editorType, player.getMainHandItem());
    }

    public static void processPlayerInventoryItemEditorRequest(ServerPlayer player, EditorType editorType, int slotIndex, boolean isCreativeInventoryScreen) {
        if (checkPermission(player)) return;
        ServerNetworkEmitter.sendPlayerInventoryItemEditorResponse(player, editorType, slotIndex, isCreativeInventoryScreen, player.getInventory().getItem(slotIndex));
    }

    public static void processBlockInventoryItemEditorRequest(ServerPlayer player, EditorType editorType, int slotIndex, BlockPos pos) {
        if (checkPermission(player)) return;
        BlockEntity blockEntity = player.getLevel().getBlockEntity(pos);
        if (blockEntity instanceof Container) {
            ServerNetworkEmitter.sendBlockInventoryItemEditorResponse(player, editorType, slotIndex, pos, ((Container) blockEntity).getItem(slotIndex));
        } else {
            player.displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ITEM), false);
        }
    }

    public static void processBlockEditorRequest(ServerPlayer player, EditorType editorType, BlockPos pos) {
        if (checkPermission(player)) return;
        ServerLevel level = player.getLevel();
        BlockState state = level.getBlockState(pos);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        CompoundTag tag = blockEntity == null ? null : blockEntity.save(new CompoundTag());
        if (tag != null || (!editorType.isNBT() && state != null)) {
            ServerNetworkEmitter.sendBlockEditorResponse(player, editorType, pos, state, tag);
        } else {
            player.displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.BLOCK), false);
        }
    }

    public static void processEntityEditorRequest(ServerPlayer player, EditorType editorType, int entityId) {
        if (checkPermission(player)) return;
        ServerLevel level = player.getLevel();
        Entity entity = level.getEntity(entityId);
        if (entity != null) {
            CompoundTag tag = entity.saveWithoutId(new CompoundTag());
            ServerNetworkEmitter.sendEntityEditorResponse(player, editorType, entityId, tag);
        } else {
            player.displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ENTITY), false);
        }
    }

    private static boolean checkPermission(ServerPlayer player) {
        if (!player.hasPermissions(CommonConfiguration.INSTANCE.getPermissionLevel()) || (CommonConfiguration.INSTANCE.isCreativeOnly() && !player.isCreative())) {
            LOGGER.warn("Player {} does not have the permission to use the editor", player.getGameProfile().getName());
            player.displayClientMessage(ModTexts.Messages.NO_PERMISSION, false);
            return true;
        }
        return false;
    }
}
