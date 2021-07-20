package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.ibeeditor.impl.common.EditorType;
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
    private static final Text ERROR_CREATIVE_ITEM = translated("ibeeditor.message.error_creative_mode").with(translated("ibeeditor.text.item")).red();
    private static final Text ERROR_SERVERMOD_ITEM = translated("ibeeditor.message.error_server_mod").with(translated("ibeeditor.text.item")).red();
    private static final Text ERROR_SERVERMOD_BLOCK = translated("ibeeditor.message.error_server_mod").with(translated("ibeeditor.text.block")).red();
    private static final Text ERROR_SERVERMOD_ENTITY = translated("ibeeditor.message.error_server_mod").with(translated("ibeeditor.text.entity")).red();

    public static void openWorldEditor(EditorType type) {
        LOGGER.debug("Opening world editor with type={}", type);
        if (!(tryOpenEntityEditor(type) || tryOpenBlockEditor(type) || tryOpenItemEditor(type))) {
            tryOpenSelfEditor(type);
        }
    }

    public static boolean tryOpenEntityEditor(EditorType type) {
        LOGGER.debug("Trying to open entity editor with type={}", type);
        WorldEntity entity = Minecraft.getClient().getEntityMouseOver();
        if (entity != null) {
            if (ClientContext.isModInstalledOnServer()) {
                requestOpenEntityEditor(entity.getEntityId(), type);
            } else {
                openEntityEditor(entity, entity.getEntityId(), type);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor(EditorType type) {
        LOGGER.debug("Trying to open block editor with type={}", type);
        WorldBlock block = Minecraft.getClient().getBlockMouseOver();
        if (block != null) {
            if (ClientContext.isModInstalledOnServer()) {
                requestOpenBlockEditor(block.getBlockPos(), type);
            } else {
                openBlockEditor(block, block.getBlockPos(), type);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenItemEditor(EditorType type) {
        LOGGER.debug("Trying to item editor with type={}", type);
        Item item = getClientPlayer().getItemMainHand();
        if (item != null) {
            openItemEditor(item, type, ClientEditorLogic::updatePlayerMainHandItem, ClientContext.isModInstalledOnServer() || getClientPlayer().isCreative() ? null : ERROR_CREATIVE_ITEM);
            return true;
        }
        return false;
    }

    public static void tryOpenSelfEditor(EditorType type) {
        LOGGER.debug("Trying to open self editor with type={}", type);
        if (ClientContext.isModInstalledOnServer()) {
            requestOpenEntityEditor(getClientPlayer().getEntityId(), type);
        } else {
            openEntityEditor(getClientPlayer(), getClientPlayer().getEntityId(), type);
        }
    }

    public static void openClipboard() {
        LOGGER.debug("Opening clipboard");
        //GameHooks.client().getScreenHandler().showScene(scene(mvc(EditorView.class, new EditorModelImpl()), true, true));
    }

    public static void openPlayerInventoryItemEditor(Item item, EditorType type, int slotId, boolean isCreativeInventoryScreen) {
        openItemEditor(item, type, newItem -> ClientEditorLogic.updatePlayerInventoryItem(newItem, slotId, isCreativeInventoryScreen),
                ClientContext.isModInstalledOnServer() || getClientPlayer().isCreative() ? null : ERROR_CREATIVE_ITEM);
    }

    public static void openBlockInventoryItemEditor(Item item, EditorType type, int slotId, BlockPos blockPos) {
        openItemEditor(item, type, newItem -> updateBlockInventoryItem(newItem, slotId, blockPos),
                ClientContext.isModInstalledOnServer() ? null : ClientEditorLogic.ERROR_SERVERMOD_ITEM);
    }

    public static void openItemEditor(Item item, EditorType type, Consumer<Item> action, Text disabledTooltip) {
        LOGGER.debug("Opening item editor for item {} with type={})", item, type);
        switch (type) {
            case STANDARD:
                ModScreenHandler.openItemEditorScreen(item, action, disabledTooltip);
                break;
            case NBT:
                ModScreenHandler.openNBTEditorScreen(item.getData(), tag -> action.accept(Minecraft.getCommon().createItem(tag)), disabledTooltip);
                break;
            case RAW_NBT:
                // TODO
                break;
        }
    }

    public static void requestOpenBlockEditor(BlockPos blockPos, EditorType type) {
        LOGGER.debug("Requesting block editor at pos {} with type={}", blockPos, type);
        ClientNetworkEmitter.sendBlockEditorRequest(blockPos, type);
    }

    public static void openBlockEditor(Block block, BlockPos blockPos, EditorType type) {
        LOGGER.debug("Opening block editor for block {} at pos {} with type={}", block, blockPos, type);
        switch (type) {
            case STANDARD:
                ModScreenHandler.openBlockEditorScreen(block, newBlock -> updateBlock(blockPos, newBlock), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_BLOCK);
                break;
            case NBT:
                ModScreenHandler.openNBTEditorScreen(block.getData(), tag -> updateBlock(blockPos, Minecraft.getCommon().createBlock(block.getState(), tag)), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_BLOCK);
                break;
            case RAW_NBT:
                // TODO
                break;
        }
    }

    public static void requestOpenEntityEditor(int entityId, EditorType type) {
        LOGGER.debug("Requesting entity editor for entityId {} with type={}", entityId, type);
        ClientNetworkEmitter.sendEntityEditorRequest(entityId, type);
    }

    public static void openEntityEditor(Entity entity, int entityId, EditorType type) {
        LOGGER.debug("Opening entity editor for entity {} with id {} and type={}", entity, entityId, type);
        switch (type) {
            case STANDARD:
                ModScreenHandler.openEntityEditorScreen(entity, entity1 -> updateEntity(entityId, entity1), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_ENTITY);
                break;
            case NBT:
                ModScreenHandler.openNBTEditorScreen(entity.getData(), tag -> updateEntity(entityId, Minecraft.getCommon().createEntity(tag)), ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_ENTITY);
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
        GUAPI.getScreenHandler().hideScene();
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
        GUAPI.getScreenHandler().hideScene();
    }

    private static void updateBlockInventoryItem(Item item, int slotId, BlockPos blockPos) {
        LOGGER.debug("Updating block inventory item {} at pos {} and in slot {}", item, blockPos, slotId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockInventoryItemUpdate(item, slotId, blockPos);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_ITEM);
        }
        GUAPI.getScreenHandler().hideScene();
    }

    private static void updateBlock(BlockPos blockPos, Block block) {
        LOGGER.debug("Updating block {} at pos {}", block, blockPos);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockUpdate(blockPos, block);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_BLOCK);
        }
        GUAPI.getScreenHandler().hideScene();
    }

    private static void updateEntity(int entityId, Entity entity) {
        LOGGER.debug("Updating entity {} with id {}", entity, entityId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendEntityUpdate(entityId, entity);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_ENTITY);
        }
        GUAPI.getScreenHandler().hideScene();
    }

    private static Player getClientPlayer() {
        return Minecraft.getClient().getPlayer();
    }
}
