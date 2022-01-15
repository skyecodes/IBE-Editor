package com.github.franckyi.ibeeditor.common;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerEditorLogic {
    private static final Logger LOGGER = LogManager.getLogger();

    /*public static void updatePlayerMainHandItem(ServerPlayer player, ItemStack itemStack) {
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
        if (blockEntity instanceof Container container) {
            container.setItem(slotId, itemStack);
            player.displayClientMessage(ModTexts.Messages.successUpdate(ModTexts.ITEM), false);
        } else {
            player.displayClientMessage(, false);
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

    public static void processMainHandItemEditorRequest(ServerPlayer player, EditorContext.EditorType editorType) {
        if (checkPermission(player)) return;
        ServerNetworkEmitter.sendMainHandItemEditorResponse(player, editorType, player.getMainHandItem());
    }

    public static void processPlayerInventoryItemEditorRequest(ServerPlayer player, EditorContext.EditorType editorType, int slotIndex, boolean isCreativeInventoryScreen) {
        if (checkPermission(player)) return;
        ServerNetworkEmitter.sendPlayerInventoryItemEditorResponse(player, editorType, slotIndex, isCreativeInventoryScreen, player.getInventory().getItem(slotIndex));
    }

    public static void processBlockInventoryItemEditorRequest(ServerPlayer player, EditorContext.EditorType editorType, int slotIndex, BlockPos pos) {
        if (checkPermission(player)) return;
        BlockEntity blockEntity = player.getLevel().getBlockEntity(pos);
        if (blockEntity instanceof Container container) {
            ServerNetworkEmitter.sendBlockInventoryItemEditorResponse(player, editorType, slotIndex, pos, container.getItem(slotIndex));
        } else {
            player.displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ITEM), false);
        }
    }

    public static void processBlockEditorRequest(ServerPlayer player, EditorContext.EditorType editorType, BlockPos pos) {
        if (checkPermission(player)) return;
        ServerLevel level = player.getLevel();
        BlockState state = level.getBlockState(pos);
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity != null || (!editorType.isNBT() && state != null)) {
            ServerNetworkEmitter.sendBlockEditorResponse(player, editorType, pos, state, entity);
        } else {
            player.displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.BLOCK), false);
        }
    }

    public static void processEntityEditorRequest(ServerPlayer player, EditorContext.EditorType editorType, int entityId) {
        if (checkPermission(player)) return;
        ServerLevel level = player.getLevel();
        Entity entity = level.getEntity(entityId);
        if (entity != null) {
            CompoundTag tag = entity.saveWithoutId(new CompoundTag());
            ServerNetworkEmitter.sendEntityEditorResponse(player, editorType, entityId, tag);
        } else {
            player.displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ENTITY), false);
        }
    }*/

    public static void processEditorRequest(EditorContext ctx, ServerPlayer player) {
        if (checkPermission(player)) {
            ctx.setSaveable(false);
            ServerNetworkEmitter.sendEditorResponse(player, ctx);
            return;
        }
        switch (ctx.getTarget()) {
            case ITEM -> ctx.setItemStack(switch (ctx.getItemInventory()) {
                case MAIN_HAND -> player.getMainHandItem();
                case PLAYER_INVENTORY -> player.getInventory().getItem(ctx.getSlotIndex());
                case BLOCK_INVENTORY -> player.getLevel().getBlockEntity(ctx.getBlockPos()) instanceof Container container
                        ? container.getItem(ctx.getSlotIndex()) : null;
            });
            case BLOCK -> {
                var level = player.getLevel();
                ctx.setBlockState(level.getBlockState(ctx.getBlockPos()));
                ctx.setBlockEntity(level.getBlockEntity(ctx.getBlockPos()));
            }
            case ENTITY -> ctx.setEntity(player.getLevel().getEntity(ctx.getEntityId()));
        }
        if (ctx.getTarget() == EditorContext.Target.ITEM && ctx.getItemStack() == null) {
            showTargetErrorMessage(player, ModTexts.ITEM);
            return;
        }
        ServerNetworkEmitter.sendEditorResponse(player, ctx);
    }

    public static void update(EditorContext ctx, ServerPlayer player) {
        if (checkPermission(player)) return;
        switch (ctx.getTarget()) {
            case ITEM -> {
                switch (ctx.getItemInventory()) {
                    case MAIN_HAND -> {
                        player.setItemInHand(InteractionHand.MAIN_HAND, ctx.getItemStack());
                        showUpdateSuccessMessage(player, ModTexts.ITEM);
                    }
                    case PLAYER_INVENTORY -> {
                        player.getInventory().setItem(ctx.getSlotIndex(), ctx.getItemStack());
                        showUpdateSuccessMessage(player, ModTexts.ITEM);
                    }
                    case BLOCK_INVENTORY -> {
                        if (player.getLevel().getBlockEntity(ctx.getBlockPos()) instanceof Container container) {
                            container.setItem(ctx.getSlotIndex(), ctx.getItemStack());
                            showUpdateSuccessMessage(player, ModTexts.ITEM);
                        } else {
                            showTargetErrorMessage(player, ModTexts.ITEM);
                        }
                    }
                }
            }
            case BLOCK -> {
                var level = player.getLevel();
                level.setBlockAndUpdate(ctx.getBlockPos(), ctx.getBlockState());
                var blockEntity = level.getBlockEntity(ctx.getBlockPos());
                if (blockEntity != null) {
                    blockEntity.load(ctx.getBlockEntity().saveWithoutMetadata());
                }
                showUpdateSuccessMessage(player, ModTexts.BLOCK);
            }
            case ENTITY -> {
                var entity = player.getLevel().getEntity(ctx.getEntityId());
                if (entity != null) {
                    entity.load(ctx.getEntity().saveWithoutId(new CompoundTag()));
                    showUpdateSuccessMessage(player, ModTexts.ENTITY);
                } else {
                    showTargetErrorMessage(player, ModTexts.ENTITY);
                }
            }
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

    private static void showUpdateSuccessMessage(ServerPlayer player, MutableComponent arg) {
        CommonUtil.showMessage(player, ModTexts.Messages.successUpdate(arg));
    }

    private static void showTargetErrorMessage(ServerPlayer player, MutableComponent arg) {
        CommonUtil.showMessage(player, ModTexts.Messages.errorNoTargetFound(arg));
    }
}
