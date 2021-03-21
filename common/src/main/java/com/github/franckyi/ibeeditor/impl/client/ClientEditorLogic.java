package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public final class ClientEditorLogic {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Text ERROR_CREATIVE_ITEM = text("You must be in creative mode to update this item.", RED);
    private static final Text ERROR_SERVERMOD_ITEM = text("IBE Editor must be installed on the server to update this item.", RED);
    private static final Text ERROR_SERVERMOD_BLOCK = text("IBE Editor must be installed on the server to update this block.", RED);
    private static final Text ERROR_SERVERMOD_ENTITY = text("IBE Editor must be installed on the server to update this entity.", RED);

    public static void openWorldEditor(boolean nbt) {
        LOGGER.debug("Opening world editor with nbt={}", nbt);
        if (!(tryOpenEntityEditor(nbt) || tryOpenBlockEditor(nbt) || tryOpenItemEditor(nbt))) {
            tryOpenSelfEditor(nbt);
        }
    }

    public static boolean tryOpenEntityEditor(boolean nbt) {
        LOGGER.debug("Trying to open entity editor with nbt={}", nbt);
        WorldEntity entity = Minecraft.getClient().getEntityMouseOver();
        if (entity != null) {
            if (ClientContext.isModInstalledOnServer()) {
                requestOpenEntityEditor(entity.getEntityId(), nbt);
            } else {
                openEntityEditor(entity, entity.getEntityId(), nbt);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor(boolean nbt) {
        LOGGER.debug("Trying to open block editor with nbt={}", nbt);
        WorldBlock block = Minecraft.getClient().getBlockMouseOver();
        if (block != null) {
            if (ClientContext.isModInstalledOnServer()) {
                requestOpenBlockEditor(block.getBlockPos(), nbt);
            } else {
                openBlockEditor(block, block.getBlockPos(), nbt);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenItemEditor(boolean nbt) {
        LOGGER.debug("Trying to item editor with nbt={}", nbt);
        Item item = getClientPlayer().getItemMainHand();
        if (item != null) {
            openItemEditor(item, nbt, ClientEditorLogic::updatePlayerMainHandItem, ClientContext.isModInstalledOnServer() || getClientPlayer().isCreative() ? null : ERROR_CREATIVE_ITEM);
            return true;
        }
        return false;
    }

    public static void tryOpenSelfEditor(boolean nbt) {
        LOGGER.debug("Trying to open self editor with nbt={}", nbt);
        if (ClientContext.isModInstalledOnServer()) {
            requestOpenEntityEditor(getClientPlayer().getEntityId(), nbt);
        } else {
            openEntityEditor(getClientPlayer(), getClientPlayer().getEntityId(), nbt);
        }
    }

    public static void openClipboard() {
        LOGGER.debug("Opening clipboard");
        //GameHooks.client().getScreenHandler().showScene(scene(mvc(EditorView.class, new EditorModelImpl()), true, true));
    }

    public static void openPlayerInventoryItemEditor(Item item, boolean nbt, int slotId, boolean isCreativeInventoryScreen) {
        openItemEditor(item, nbt, newItem -> ClientEditorLogic.updatePlayerInventoryItem(newItem, slotId, isCreativeInventoryScreen),
                ClientContext.isModInstalledOnServer() || getClientPlayer().isCreative() ? null : ERROR_CREATIVE_ITEM);
    }

    public static void openBlockInventoryItemEditor(Item item, boolean nbt, int slotId, BlockPos blockPos) {
        openItemEditor(item, nbt, newItem -> updateBlockInventoryItem(newItem, slotId, blockPos),
                ClientContext.isModInstalledOnServer() ? null : ClientEditorLogic.ERROR_SERVERMOD_ITEM);
    }

    public static void openItemEditor(Item item, boolean nbt, Consumer<Item> action, Text disabledTooltip) {
        LOGGER.debug("Opening item editor for item {} with nbt={})", item, nbt);
        if (nbt) {
            EditorHandler.openNBTEditor(item.getTag(), tag -> action.accept(Minecraft.getCommon().createItem(tag)), disabledTooltip);
        } else {
            EditorHandler.openItemEditor(item, action, disabledTooltip);
        }
    }

    public static void requestOpenBlockEditor(BlockPos blockPos, boolean nbt) {
        LOGGER.debug("Requesting block editor at pos {} with nbt={}", blockPos, nbt);
        ClientNetworkEmitter.sendBlockEditorRequest(blockPos, nbt);
    }

    public static void openBlockEditor(Block block, BlockPos blockPos, boolean nbt) {
        LOGGER.debug("Opening block editor for block {} at pos {} with nbt={}", block, blockPos, nbt);
        if (nbt) {
            EditorHandler.openNBTEditor(block.getData(), tag -> updateBlock(blockPos, Minecraft.getCommon().createBlock(block.getState(), tag)), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_BLOCK);
        } else {
            EditorHandler.openBlockEditor(block, newBlock -> updateBlock(blockPos, newBlock), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_BLOCK);
        }
    }

    public static void requestOpenEntityEditor(int entityId, boolean nbt) {
        LOGGER.debug("Requesting entity editor for entityId {} with nbt={}", entityId, nbt);
        ClientNetworkEmitter.sendEntityEditorRequest(entityId, nbt);
    }

    public static void openEntityEditor(Entity entity, int entityId, boolean nbt) {
        LOGGER.debug("Opening entity editor for entity {} with id {} and nbt={}", entity, entityId, nbt);
        if (nbt) {
            EditorHandler.openNBTEditor(entity.getTag(), tag -> updateEntity(entityId, Minecraft.getCommon().createEntity(tag)), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_ENTITY);
        } else {
            EditorHandler.openEntityEditor(entity, entity1 -> updateEntity(entityId, entity1), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_ENTITY);
        }
    }

    private static void updatePlayerMainHandItem(Item item) {
        LOGGER.debug("Updating player main hand item {}", item);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendPlayerMainHandItemUpdate(item);
        } else {
            if (getClientPlayer().isCreative()) {
                getClientPlayer().updateMainHandItem(item);
            } else {
                getClientPlayer().sendMessage(ERROR_CREATIVE_ITEM);
            }
        }
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updatePlayerInventoryItem(Item item, int slotId, boolean isCreativeInventoryScreen) {
        LOGGER.debug("Updating player inventory item {} in slot {}", item, slotId);
        if (ClientContext.isModInstalledOnServer())  {
            ClientNetworkEmitter.sendPlayerInventoryItemUpdate(item, slotId);
        } else {
            if (getClientPlayer().isCreative()) {
                if (isCreativeInventoryScreen) {
                    getClientPlayer().updateCreativeInventoryItem(item, slotId);
                } else {
                    getClientPlayer().updateInventoryItem(item, slotId);
                }
            } else {
                getClientPlayer().sendMessage(ERROR_CREATIVE_ITEM);
            }
        }
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updateBlockInventoryItem(Item item, int slotId, BlockPos blockPos) {
        LOGGER.debug("Updating block inventory item {} at pos {} and in slot {}", item, blockPos, slotId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockInventoryItemUpdate(item, slotId, blockPos);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_ITEM);
        }
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updateBlock(BlockPos blockPos, Block block) {
        LOGGER.debug("Updating block {} at pos {}", block, blockPos);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockUpdate(blockPos, block);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_BLOCK);
        }
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updateEntity(int entityId, Entity entity) {
        LOGGER.debug("Updating entity {} with id {}", entity, entityId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendEntityUpdate(entityId, entity);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_ENTITY);
        }
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static Player getClientPlayer() {
        return Minecraft.getClient().getPlayer();
    }
}
