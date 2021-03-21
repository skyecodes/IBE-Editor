package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.world.Block;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.api.common.world.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public final class ServerEditorLogic {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void updatePlayerMainHandItem(Player player, Item item) {
        LOGGER.debug("Updating {}'s main hand item to {}", player.toString(), item);
        player.setItemMainHand(item);
        player.sendMessage(text("Item updated!", GREEN));
    }

    public static void updatePlayerInventoryItem(Player player, Item item, int slotId) {
        LOGGER.debug("Updating {}'s inventory item at slot {} to {}", player.toString(), slotId, item);
        player.setInventoryItem(slotId, item);
        player.sendMessage(text("Item updated!", GREEN));
    }

    public static void updateBlockInventoryItem(Player sender, Item item, int slotId, BlockPos blockPos) {
        LOGGER.debug("Updating block inventory item at pos {} and slot {} to {}", blockPos, slotId, item);
        sender.getWorld().setBlockInventoryItem(blockPos, slotId, item);
        sender.sendMessage(text("Item updated!", GREEN));
    }

    public static void updateBlock(Player sender, Block block, BlockPos blockPos) {
        LOGGER.debug("Updating block {} at pos {}", block, blockPos);
        sender.getWorld().setBlockData(blockPos, block);
        sender.sendMessage(text("Block updated!", GREEN));
    }

    public static void updateEntity(Player sender, Entity entity, int entityId) {
        LOGGER.debug("Updating entity {} with id {}", entity, entityId);
        sender.getWorld().setEntityData(entityId, entity);
        sender.sendMessage(text("Entity updated!", GREEN));
    }
}
