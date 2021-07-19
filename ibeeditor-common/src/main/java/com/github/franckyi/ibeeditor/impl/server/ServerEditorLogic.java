package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Block;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.api.common.world.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public final class ServerEditorLogic {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Text ITEM_UPDATED = translated("ibeeditor.message.success_update").with(translated("ibeeditor.text.item")).green();
    private static final Text BLOCK_UPDATED = translated("ibeeditor.message.success_update").with(translated("ibeeditor.text.block")).green();
    private static final Text ENTITY_UPDATED = translated("ibeeditor.message.success_update").with(translated("ibeeditor.text.entity")).green();

    public static void updatePlayerMainHandItem(Player player, Item item) {
        LOGGER.debug("Updating {}'s main hand item to {}", player.toString(), item);
        LOGGER.debug(item.getData());
        player.setItemMainHand(item);
        player.sendMessage(ITEM_UPDATED);
    }

    public static void updatePlayerInventoryItem(Player player, Item item, int slotId) {
        LOGGER.debug("Updating {}'s inventory item at slot {} to {}", player.toString(), slotId, item);
        LOGGER.debug(item.getData());
        player.setInventoryItem(slotId, item);
        player.sendMessage(ITEM_UPDATED);
    }

    public static void updateBlockInventoryItem(Player sender, Item item, int slotId, BlockPos blockPos) {
        LOGGER.debug("Updating block inventory item at pos {} and slot {} to {}", blockPos, slotId, item);
        LOGGER.debug(item.getData());
        sender.getWorld().setBlockInventoryItem(blockPos, slotId, item);
        sender.sendMessage(ITEM_UPDATED);
    }

    public static void updateBlock(Player sender, Block block, BlockPos blockPos) {
        LOGGER.debug("Updating block {} at pos {}", block, blockPos);
        LOGGER.debug(block.getData());
        sender.getWorld().setBlockData(blockPos, block);
        sender.sendMessage(BLOCK_UPDATED);
    }

    public static void updateEntity(Player sender, Entity entity, int entityId) {
        LOGGER.debug("Updating entity {} with id {}", entity, entityId);
        LOGGER.debug(entity.getData());
        sender.getWorld().setEntityData(entityId, entity);
        sender.sendMessage(ENTITY_UPDATED);
    }
}
