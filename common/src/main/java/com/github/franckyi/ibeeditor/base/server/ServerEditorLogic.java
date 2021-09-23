package com.github.franckyi.ibeeditor.base.server;

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
        LOGGER.debug("Updating {}'s main hand item to {}", player.getGameProfile().getName(), itemStack);
        LOGGER.debug(itemStack.save(new CompoundTag()));
        player.setItemInHand(InteractionHand.MAIN_HAND, itemStack);
        player.displayClientMessage(ModTexts.Messages.successUpdate(ModTexts.ITEM), false);
    }

    public static void updatePlayerInventoryItem(ServerPlayer player, ItemStack itemStack, int slotId) {
        LOGGER.debug("Updating {}'s inventory item at slot {} to {}", player.getGameProfile().getName(), slotId, itemStack);
        LOGGER.debug(itemStack.save(new CompoundTag()));
        player.inventory.setItem(slotId, itemStack);
        player.displayClientMessage(ModTexts.Messages.successUpdate(ModTexts.ITEM), false);
    }

    public static void updateBlockInventoryItem(ServerPlayer sender, ItemStack itemStack, int slotId, BlockPos blockPos) {
        LOGGER.debug("Updating block inventory item at pos {} and slot {} to {}", blockPos, slotId, itemStack);
        LOGGER.debug(itemStack.save(new CompoundTag()));
        ServerLevel level = sender.getLevel();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof Container) {
            ((Container) blockEntity).setItem(slotId, itemStack);
            sender.displayClientMessage(ModTexts.Messages.successUpdate(ModTexts.ITEM), false);
        } else {
            sender.displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ITEM), false);
        }
    }

    public static void updateBlock(ServerPlayer sender, BlockPos pos, BlockState state, CompoundTag tag) {
        LOGGER.debug("Updating block {} at pos {}", state, pos);
        LOGGER.debug(tag);
        ServerLevel level = sender.getLevel();
        level.setBlockAndUpdate(pos, state);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity != null) {
            blockEntity.load(state, tag);
        }
        sender.displayClientMessage(ModTexts.Messages.successUpdate(ModTexts.BLOCK), false);
    }

    public static void updateEntity(ServerPlayer sender, CompoundTag tag, int entityId) {
        LOGGER.debug("Updating entity {} with id {}", tag, entityId);
        LOGGER.debug(tag);
        Entity entity = sender.getLevel().getEntity(entityId);
        if (entity != null) {
            entity.load(tag);
            sender.displayClientMessage(ModTexts.Messages.successUpdate(ModTexts.ENTITY), false);
        } else {
            sender.displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.ENTITY), false);
        }
    }

    public static void processBlockEditorRequest(ServerPlayer sender, EditorType editorType, BlockPos pos) {
        ServerLevel level = sender.getLevel();
        BlockState state = level.getBlockState(pos);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        CompoundTag tag = blockEntity == null ? null : blockEntity.save(new CompoundTag());
        ServerNetworkEmitter.sendBlockEditorResponse(sender, editorType, pos, state, tag);
    }

    public static void processEntityEditorRequest(ServerPlayer sender, EditorType editorType, int entityId) {
        ServerLevel level = sender.getLevel();
        Entity entity = level.getEntity(entityId);
        if (entity != null) {
            CompoundTag tag = entity.saveWithoutId(new CompoundTag());
            ServerNetworkEmitter.sendEntityEditorResponse(sender, editorType, entityId, tag);
        }
    }
}
