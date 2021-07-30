package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.BlockPos;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.*;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.Messages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GuapiHelper.*;

public final class ClientEditorLogic {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Text ERROR_CREATIVE_ITEM = Messages.withPrefix(translated("ibeeditor.message.error_creative_mode")
            .with(translated("ibeeditor.text.item"))).red();
    private static final Text ERROR_SERVERMOD_ITEM = Messages.withPrefix(translated("ibeeditor.message.error_server_mod")
            .with(translated("ibeeditor.text.item"))).red();
    private static final Text ERROR_SERVERMOD_BLOCK = Messages.withPrefix(translated("ibeeditor.message.error_server_mod")
            .with(translated("ibeeditor.text.block"))).red();
    private static final Text ERROR_SERVERMOD_ENTITY = Messages.withPrefix(translated("ibeeditor.message.error_server_mod")
            .with(translated("ibeeditor.text.entity"))).red();

    public static void openWorldEditor(EditorType target) {
        LOGGER.debug("Opening world editor with target={}", target);
        if (!(tryOpenEntityEditor(target) || tryOpenBlockEditor(target) || tryOpenItemEditor(target))) {
            tryOpenSelfEditor(target);
        }
    }

    public static boolean tryOpenEntityEditor(EditorType target) {
        LOGGER.debug("Trying to open entity editor with target={}", target);
        WorldEntity entity = Game.getClient().getEntityMouseOver();
        if (entity != null) {
            if (ClientContext.isModInstalledOnServer()) {
                requestOpenEntityEditor(entity.getEntityId(), target);
            } else {
                openEntityEditor(entity, entity.getEntityId(), target);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor(EditorType target) {
        LOGGER.debug("Trying to open block editor with target={}", target);
        WorldBlock block = Game.getClient().getBlockMouseOver();
        if (block != null) {
            if (ClientContext.isModInstalledOnServer()) {
                requestOpenBlockEditor(block.getBlockPos(), target);
            } else {
                openBlockEditor(block, block.getBlockPos(), target);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenItemEditor(EditorType target) {
        LOGGER.debug("Trying to item editor with target={}", target);
        Item item = getClientPlayer().getItemMainHand();
        if (item != null) {
            openItemEditor(item, target, ClientEditorLogic::updatePlayerMainHandItem, ClientContext.isModInstalledOnServer() || getClientPlayer().isCreative() ? null : ERROR_CREATIVE_ITEM);
            return true;
        }
        return false;
    }

    public static void tryOpenSelfEditor(EditorType target) {
        LOGGER.debug("Trying to open self editor with target={}", target);
        if (ClientContext.isModInstalledOnServer()) {
            requestOpenEntityEditor(getClientPlayer().getEntityId(), target);
        } else {
            openEntityEditor(getClientPlayer(), getClientPlayer().getEntityId(), target);
        }
    }

    public static void openClipboard() {
        //LOGGER.debug("Opening clipboard");
        //GameHooks.client().getScreenHandler().showScene(scene(mvc(EditorView.class, new EditorModelImpl()), true, true));
    }

    public static void openPlayerInventoryItemEditor(Item item, EditorType target, int slotId, boolean isCreativeInventoryScreen) {
        openItemEditor(item, target, newItem -> ClientEditorLogic.updatePlayerInventoryItem(newItem, slotId, isCreativeInventoryScreen),
                ClientContext.isModInstalledOnServer() || getClientPlayer().isCreative() ? null : ERROR_CREATIVE_ITEM);
    }

    public static void openBlockInventoryItemEditor(Item item, EditorType target, int slotId, BlockPos blockPos) {
        openItemEditor(item, target, newItem -> updateBlockInventoryItem(newItem, slotId, blockPos),
                ClientContext.isModInstalledOnServer() ? null : ClientEditorLogic.ERROR_SERVERMOD_ITEM);
    }

    public static void openItemEditor(Item item, EditorType target, Consumer<Item> action, Text disabledTooltip) {
        LOGGER.debug("Opening item editor for item {} with target={})", item, target);
        switch (target) {
            case STANDARD:
                ModScreenHandler.openItemEditorScreen(item, action, disabledTooltip);
                break;
            case NBT:
                ModScreenHandler.openNBTEditorScreen(item.getData(), tag -> action.accept(Game.getCommon().createItem(tag)), disabledTooltip);
                break;
            case RAW_NBT:
                // TODO
                break;
        }
    }

    public static void requestOpenBlockEditor(BlockPos blockPos, EditorType target) {
        LOGGER.debug("Requesting block editor at pos {} with target={}", blockPos, target);
        ClientNetworkEmitter.sendBlockEditorRequest(blockPos, target);
    }

    public static void openBlockEditor(Block block, BlockPos blockPos, EditorType target) {
        LOGGER.debug("Opening block editor for block {} at pos {} with target={}", block, blockPos, target);
        switch (target) {
            case STANDARD:
                ModScreenHandler.openBlockEditorScreen(block, newBlock -> updateBlock(blockPos, newBlock), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_BLOCK);
                break;
            case NBT:
                ModScreenHandler.openNBTEditorScreen(block.getData(), tag -> updateBlock(blockPos, Game.getCommon().createBlock(block.getState(), tag)), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_BLOCK);
                break;
            case RAW_NBT:
                // TODO
                break;
        }
    }

    public static void requestOpenEntityEditor(int entityId, EditorType target) {
        LOGGER.debug("Requesting entity editor for entityId {} with target={}", entityId, target);
        ClientNetworkEmitter.sendEntityEditorRequest(entityId, target);
    }

    public static void openEntityEditor(Entity entity, int entityId, EditorType target) {
        LOGGER.debug("Opening entity editor for entity {} with id {} and target={}", entity, entityId, target);
        switch (target) {
            case STANDARD:
                ModScreenHandler.openEntityEditorScreen(entity, entity1 -> updateEntity(entityId, entity1), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_ENTITY);
                break;
            case NBT:
                ModScreenHandler.openNBTEditorScreen(entity.getData(), tag -> updateEntity(entityId, Game.getCommon().createEntity(tag)), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_ENTITY);
                break;
            case RAW_NBT:
                // TODO
                break;
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
        Guapi.getScreenHandler().hideScene();
    }

    private static void updatePlayerInventoryItem(Item item, int slotId, boolean isCreativeInventoryScreen) {
        LOGGER.debug("Updating player inventory item {} in slot {}", item, slotId);
        if (ClientContext.isModInstalledOnServer()) {
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
        Guapi.getScreenHandler().hideScene();
    }

    private static void updateBlockInventoryItem(Item item, int slotId, BlockPos blockPos) {
        LOGGER.debug("Updating block inventory item {} at pos {} and in slot {}", item, blockPos, slotId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockInventoryItemUpdate(item, slotId, blockPos);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_ITEM);
        }
        Guapi.getScreenHandler().hideScene();
    }

    private static void updateBlock(BlockPos blockPos, Block block) {
        LOGGER.debug("Updating block {} at pos {}", block, blockPos);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockUpdate(blockPos, block);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_BLOCK);
        }
        Guapi.getScreenHandler().hideScene();
    }

    private static void updateEntity(int entityId, Entity entity) {
        LOGGER.debug("Updating entity {} with id {}", entity, entityId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendEntityUpdate(entityId, entity);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_ENTITY);
        }
        Guapi.getScreenHandler().hideScene();
    }

    private static Player getClientPlayer() {
        return Game.getClient().getPlayer();
    }
}
