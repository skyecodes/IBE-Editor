package com.github.franckyi.ibeeditor.common;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerEditorLogic {
    private static final Logger LOGGER = LogManager.getLogger();

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
                    entity.load(ctx.getTag());
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
