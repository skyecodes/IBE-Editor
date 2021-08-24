package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.world.IBlockPos;
import com.github.franckyi.gameadapter.api.common.world.IEntity;
import com.github.franckyi.gameadapter.api.common.world.WorldBlockData;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public final class ClientEditorLogic {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void openWorldEditor(EditorType target) {
        LOGGER.debug("Opening world editor with target={}", target);
        if (!(tryOpenEntityEditor(target) || tryOpenBlockEditor(target) || tryOpenItemEditor(target))) {
            tryOpenSelfEditor(target);
        }
    }

    public static boolean tryOpenEntityEditor(EditorType target) {
        LOGGER.debug("Trying to open entity editor with target={}", target);
        IEntity entity = Game.getClient().getEntityMouseOver();
        if (entity != null) {
            if (ClientContext.isModInstalledOnServer()) {
                requestOpenEntityEditor(entity.getEntityId(), target);
            } else {
                openEntityEditor(entity.getData(), entity.getEntityId(), target);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor(EditorType target) {
        LOGGER.debug("Trying to open block editor with target={}", target);
        WorldBlockData block = Game.getClient().getBlockMouseOver();
        if (block != null) {
            if (ClientContext.isModInstalledOnServer()) {
                requestOpenBlockEditor(block.getPos(), target);
            } else {
                openBlockEditor(block, target);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenItemEditor(EditorType target) {
        LOGGER.debug("Trying to item editor with target={}", target);
        IItemStack itemStack = player().getItemMainHand();
        if (itemStack != null && !itemStack.isEmpty()) {
            openItemEditor(itemStack, target, ClientEditorLogic::updatePlayerMainHandItem,
                    ClientContext.isModInstalledOnServer() || player().isCreative() ? null : ModTexts.ERROR_CREATIVE_ITEM);
            return true;
        }
        return false;
    }

    public static void tryOpenSelfEditor(EditorType target) {
        LOGGER.debug("Trying to open self editor with target={}", target);
        if (ClientContext.isModInstalledOnServer()) {
            requestOpenEntityEditor(player().getEntityId(), target);
        } else {
            openEntityEditor(player().getData(), player().getEntityId(), target);
        }
    }

    public static void openClipboard() {
        //LOGGER.debug("Opening clipboard");
        //GameHooks.client().getScreenHandler().showScene(scene(mvc(EditorView.class, new EditorModelImpl()), true, true));
    }

    public static void openPlayerInventoryItemEditor(IItemStack itemStack, EditorType target, int slotId, boolean isCreativeInventoryScreen) {
        openItemEditor(itemStack, target,
                newItem -> updatePlayerInventoryItem(newItem, slotId, isCreativeInventoryScreen),
                ClientContext.isModInstalledOnServer() || player().isCreative() ? null : ModTexts.ERROR_CREATIVE_ITEM);
    }

    public static void openBlockInventoryItemEditor(IItemStack itemStack, EditorType target, int slotId, IBlockPos blockPos) {
        openItemEditor(itemStack, target,
                newItem -> updateBlockInventoryItem(newItem, slotId, blockPos),
                ClientContext.isModInstalledOnServer() ? null : ModTexts.ERROR_SERVERMOD_ITEM);
    }

    public static void openItemEditor(IItemStack itemStack, EditorType target, Consumer<IItemStack> action, IText disabledTooltip) {
        LOGGER.debug("Opening item editor for item {} with target={})", itemStack, target);
        switch (target) {
            case STANDARD:
                ModScreenHandler.openItemEditorScreen(itemStack, action, disabledTooltip);
                break;
            case NBT:
                ModScreenHandler.openNBTEditorScreen(itemStack.getData(),
                        tag -> action.accept(IItemStack.fromTag(tag)), disabledTooltip);
                break;
            case SNBT:
                ModScreenHandler.openSNBTEditorScreen(itemStack.getData().toString(),
                        snbt -> action.accept(IItemStack.fromTag(ICompoundTag.parse(snbt))), disabledTooltip);
                break;
        }
    }

    public static void requestOpenBlockEditor(IBlockPos blockPos, EditorType target) {
        LOGGER.debug("Requesting block editor at pos {} with target={}", blockPos, target);
        ClientNetworkEmitter.sendBlockEditorRequest(blockPos, target);
    }

    public static void openBlockEditor(WorldBlockData block, EditorType target) {
        LOGGER.debug("Opening block editor for block {} at pos {} with target={}", block.getState(), block.getPos(), target);
        switch (target) {
            case STANDARD:
                /*ModScreenHandler.openBlockEditorScreen(block,
                        newBlock -> updateBlock(new WorldBlockData(newBlock, block.getPos())),
                        ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_BLOCK);*/
                player().sendMessage(ModTexts.ERROR_NOT_IMPLEMENTED_BLOCK);
                break;
            case NBT:
                if (block.getTag() == null) {
                    player().sendMessage(ModTexts.NO_BLOCK_FOUND_TEXT);
                    break;
                }
                ModScreenHandler.openNBTEditorScreen(block.getTag(),
                        tag -> updateBlock(new WorldBlockData(block.getState(), tag, block.getPos())),
                        ClientContext.isModInstalledOnServer() ? null : ModTexts.ERROR_SERVERMOD_BLOCK);
                break;
            case SNBT:
                if (block.getTag() == null) {
                    player().sendMessage(ModTexts.NO_BLOCK_FOUND_TEXT);
                    break;
                }
                ModScreenHandler.openSNBTEditorScreen(block.getTag().toString(),
                        snbt -> updateBlock(new WorldBlockData(block.getState(), ICompoundTag.parse(snbt), block.getPos())),
                        ClientContext.isModInstalledOnServer() ? null : ModTexts.ERROR_SERVERMOD_BLOCK);
                break;
        }
    }

    public static void requestOpenEntityEditor(int entityId, EditorType target) {
        LOGGER.debug("Requesting entity editor for entityId {} with target={}", entityId, target);
        ClientNetworkEmitter.sendEntityEditorRequest(entityId, target);
    }

    public static void openEntityEditor(ICompoundTag entity, int entityId, EditorType target) {
        LOGGER.debug("Opening entity editor for entity {} with id {} and target={}", entity, entityId, target);
        switch (target) {
            case STANDARD:
                /*ModScreenHandler.openEntityEditorScreen(entity,
                        entity1 -> updateEntity(entityId, entity1),
                        ClientContext.isModInstalledOnServer() ? null : ERROR_SERVERMOD_ENTITY);*/
                player().sendMessage(ModTexts.ERROR_NOT_IMPLEMENTED_ENTITY);
                break;
            case NBT:
                ModScreenHandler.openNBTEditorScreen(entity,
                        tag -> updateEntity(entityId, tag),
                        ClientContext.isModInstalledOnServer() ? null : ModTexts.ERROR_SERVERMOD_ENTITY);
                break;
            case SNBT:
                ModScreenHandler.openSNBTEditorScreen(entity.toString(),
                        snbt -> updateEntity(entityId, ICompoundTag.parse(snbt)),
                        ClientContext.isModInstalledOnServer() ? null : ModTexts.ERROR_SERVERMOD_ENTITY);
                break;
        }
    }

    private static void updatePlayerMainHandItem(IItemStack itemStack) {
        LOGGER.debug("Updating player main hand item {}", itemStack);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendPlayerMainHandItemUpdate(itemStack);
        } else {
            if (player().isCreative()) {
                player().updateMainHandItem(itemStack);
            } else {
                player().sendMessage(ModTexts.ERROR_CREATIVE_ITEM);
            }
        }
        Guapi.getScreenHandler().hideScene();
    }

    private static void updatePlayerInventoryItem(IItemStack itemStack, int slotId, boolean isCreativeInventoryScreen) {
        LOGGER.debug("Updating player inventory item {} in slot {}", itemStack, slotId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendPlayerInventoryItemUpdate(itemStack, slotId);
        } else {
            if (player().isCreative()) {
                if (isCreativeInventoryScreen) {
                    player().setInventoryItem(itemStack, slotId);
                } else {
                    Game.getClient().updateInventoryItem(itemStack, slotId);
                }
            } else {
                player().sendMessage(ModTexts.ERROR_CREATIVE_ITEM);
            }
        }
        Guapi.getScreenHandler().hideScene();
    }

    private static void updateBlockInventoryItem(IItemStack itemStack, int slotId, IBlockPos blockPos) {
        LOGGER.debug("Updating block inventory item {} at pos {} and in slot {}", itemStack, blockPos, slotId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockInventoryItemUpdate(itemStack, slotId, blockPos);
        } else {
            player().sendMessage(ModTexts.ERROR_SERVERMOD_ITEM);
        }
        Guapi.getScreenHandler().hideScene();
    }

    private static void updateBlock(WorldBlockData block) {
        LOGGER.debug("Updating block {} at pos {}", block, block.getPos());
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockUpdate(block);
        } else {
            player().sendMessage(ModTexts.ERROR_SERVERMOD_BLOCK);
        }
        Guapi.getScreenHandler().hideScene();
    }

    private static void updateEntity(int entityId, ICompoundTag tag) {
        LOGGER.debug("Updating entity {} with id {}", tag.toString(), entityId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendEntityUpdate(entityId, tag);
        } else {
            player().sendMessage(ModTexts.ERROR_SERVERMOD_ENTITY);
        }
        Guapi.getScreenHandler().hideScene();
    }

    private static IPlayer player() {
        return IPlayer.client();
    }
}
