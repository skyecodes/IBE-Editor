package com.github.franckyi.ibeeditor.base.server;

import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.world.IBlockPos;
import com.github.franckyi.gameadapter.api.common.world.WorldBlockData;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.github.franckyi.guapi.GuapiHelper.*;

public final class ServerEditorLogic {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final IText ITEM_UPDATED = ModTexts.prefixed(translated("ibeeditor.message.success_update", translated("ibeeditor.text.item"))).green();
    private static final IText BLOCK_UPDATED = ModTexts.prefixed(translated("ibeeditor.message.success_update", translated("ibeeditor.text.block"))).green();
    private static final IText ENTITY_UPDATED = ModTexts.prefixed(translated("ibeeditor.message.success_update", translated("ibeeditor.text.entity"))).green();

    public static void updatePlayerMainHandItem(IPlayer player, IItemStack itemStack) {
        LOGGER.debug("Updating {}'s main hand item to {}", player.getProfileName(), itemStack);
        LOGGER.debug(itemStack.getData());
        player.setItemMainHand(itemStack);
        player.sendMessage(ITEM_UPDATED);
    }

    public static void updatePlayerInventoryItem(IPlayer player, IItemStack itemStack, int slotId) {
        LOGGER.debug("Updating {}'s inventory item at slot {} to {}", player.getProfileName(), slotId, itemStack);
        LOGGER.debug(itemStack.getData());
        player.setInventoryItem(itemStack, slotId);
        player.sendMessage(ITEM_UPDATED);
    }

    public static void updateBlockInventoryItem(IPlayer sender, IItemStack itemStack, int slotId, IBlockPos blockPos) {
        LOGGER.debug("Updating block inventory item at pos {} and slot {} to {}", blockPos, slotId, itemStack);
        LOGGER.debug(itemStack.getData());
        sender.getWorld().setBlockInventoryItem(blockPos, slotId, itemStack);
        sender.sendMessage(ITEM_UPDATED);
    }

    public static void updateBlock(IPlayer sender, WorldBlockData block) {
        LOGGER.debug("Updating block {} at pos {}", block, block.getPos());
        LOGGER.debug(block.getTag());
        sender.getWorld().setBlockData(block);
        sender.sendMessage(BLOCK_UPDATED);
    }

    public static void updateEntity(IPlayer sender, ICompoundTag entity, int entityId) {
        LOGGER.debug("Updating entity {} with id {}", entity, entityId);
        LOGGER.debug(entity);
        sender.getWorld().setEntityData(entityId, entity);
        sender.sendMessage(ENTITY_UPDATED);
    }
}
