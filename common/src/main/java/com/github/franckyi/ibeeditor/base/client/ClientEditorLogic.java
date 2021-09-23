package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.guapi.base.GuapiScreenHandler;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
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
        HitResult result = Minecraft.getInstance().hitResult;
        if (result instanceof EntityHitResult) {
            Entity entity = ((EntityHitResult) result).getEntity();
            if (ClientContext.isModInstalledOnServer()) {
                requestOpenEntityEditor(entity.getId(), target);
            } else {
                openEntityEditor(entity.saveWithoutId(new CompoundTag()), entity.getId(), target);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor(EditorType target) {
        LOGGER.debug("Trying to open block editor with target={}", target);
        HitResult result = Minecraft.getInstance().hitResult;
        if (result instanceof BlockHitResult) {
            BlockHitResult res = (BlockHitResult) result;
            ClientLevel level = Minecraft.getInstance().level;
            if (!level.isEmptyBlock(res.getBlockPos())) {
                BlockPos pos = res.getBlockPos();
                if (ClientContext.isModInstalledOnServer()) {
                    requestOpenBlockEditor(pos, target);
                } else {
                    BlockState state = level.getBlockState(pos);
                    BlockEntity entity = level.getBlockEntity(pos);
                    CompoundTag tag = entity == null ? null : entity.save(new CompoundTag());
                    openBlockEditor(pos, state, tag, target);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean tryOpenItemEditor(EditorType target) {
        LOGGER.debug("Trying to item editor with target={}", target);
        ItemStack itemStack = player().getItemInHand(InteractionHand.MAIN_HAND);
        if (itemStack != null && !itemStack.isEmpty()) {
            openItemEditor(itemStack, target, ClientEditorLogic::updatePlayerMainHandItem,
                    getDisabledTooltipCreativeMode(ModTexts.ITEM));
            return true;
        }
        return false;
    }

    public static void tryOpenSelfEditor(EditorType target) {
        LOGGER.debug("Trying to open self editor with target={}", target);
        if (ClientContext.isModInstalledOnServer()) {
            requestOpenEntityEditor(player().getId(), target);
        } else {
            openEntityEditor(player().saveWithoutId(new CompoundTag()), player().getId(), target);
        }
    }

    public static void openVault() {
        //LOGGER.debug("Opening vault");
        //ModScreenHandler.openVault();
    }

    public static void openPlayerInventoryItemEditor(ItemStack itemStack, EditorType target, int slotId, boolean isCreativeInventoryScreen) {
        openItemEditor(itemStack, target,
                newItem -> updatePlayerInventoryItem(newItem, slotId, isCreativeInventoryScreen),
                getDisabledTooltipCreativeMode(ModTexts.ITEM));
    }

    public static void openBlockInventoryItemEditor(ItemStack itemStack, EditorType target, int slotId, BlockPos blockPos) {
        openItemEditor(itemStack, target,
                newItem -> updateBlockInventoryItem(newItem, slotId, blockPos),
                getDisabledTooltipServerMod(ModTexts.ITEM));
    }

    public static void openItemEditor(ItemStack itemStack, EditorType target, Consumer<ItemStack> action, Component disabledTooltip) {
        LOGGER.debug("Opening item editor for item {} with target={})", itemStack, target);
        switch (target) {
            case STANDARD:
                ModScreenHandler.openItemEditorScreen(itemStack, action, disabledTooltip);
                break;
            case NBT:
                ModScreenHandler.openNBTEditorScreen(itemStack.save(new CompoundTag()),
                        tag -> action.accept(ItemStack.of(tag)), disabledTooltip);
                break;
            case SNBT:
                ModScreenHandler.openSNBTEditorScreen(itemStack.save(new CompoundTag()).toString(),
                        snbt -> action.accept(ItemStack.of(parseTag(snbt))), disabledTooltip);
                break;
        }
    }

    public static void requestOpenBlockEditor(BlockPos pos, EditorType target) {
        LOGGER.debug("Requesting block editor at pos {} with target={}", pos, target);
        ClientNetworkEmitter.sendBlockEditorRequest(pos, target);
    }

    public static void openBlockEditor(BlockPos pos, BlockState state, CompoundTag tag, EditorType target) {
        LOGGER.debug("Opening block editor for block {} at pos {} with target={}", state, pos, target);
        switch (target) {
            case STANDARD:
                /*ModScreenHandler.openBlockEditorScreen(block,
                        newBlock -> updateBlock(new WorldBlockData(newBlock, block.getPos())),
                        getDisabledTooltipServerMod(ModTexts.BLOCK));*/
                player().displayClientMessage(ModTexts.Messages.warnNotImplemented(ModTexts.BLOCK), false);
                break;
            case NBT:
                if (tag == null) {
                    player().displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.BLOCK), false);
                    break;
                }
                ModScreenHandler.openNBTEditorScreen(tag,
                        newTag -> updateBlock(pos, state, newTag),
                        getDisabledTooltipServerMod(ModTexts.BLOCK));
                break;
            case SNBT:
                if (tag == null) {
                    player().displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.BLOCK), false);
                    break;
                }
                ModScreenHandler.openSNBTEditorScreen(tag.toString(),
                        snbt -> updateBlock(pos, state, parseTag(snbt)),
                        getDisabledTooltipServerMod(ModTexts.BLOCK));
                break;
        }
    }

    public static void requestOpenEntityEditor(int entityId, EditorType target) {
        LOGGER.debug("Requesting entity editor for entityId {} with target={}", entityId, target);
        ClientNetworkEmitter.sendEntityEditorRequest(entityId, target);
    }

    public static void openEntityEditor(CompoundTag entity, int entityId, EditorType target) {
        LOGGER.debug("Opening entity editor for entity {} with id {} and target={}", entity, entityId, target);
        switch (target) {
            case STANDARD:
                /*ModScreenHandler.openEntityEditorScreen(entity,
                        entity1 -> updateEntity(entityId, entity1),
                        getDisabledTooltipServerMod(ModTexts.ENTITY));*/
                player().displayClientMessage(ModTexts.Messages.warnNotImplemented(ModTexts.ENTITY), false);
                break;
            case NBT:
                ModScreenHandler.openNBTEditorScreen(entity,
                        tag -> updateEntity(entityId, tag),
                        getDisabledTooltipServerMod(ModTexts.ENTITY));
                break;
            case SNBT:
                ModScreenHandler.openSNBTEditorScreen(entity.toString(),
                        snbt -> updateEntity(entityId, parseTag(snbt)),
                        getDisabledTooltipServerMod(ModTexts.ENTITY));
                break;
        }
    }

    private static void updatePlayerMainHandItem(ItemStack itemStack) {
        LOGGER.debug("Updating player main hand item {}", itemStack);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendPlayerMainHandItemUpdate(itemStack);
        } else {
            if (player().isCreative()) {
                player().setItemInHand(InteractionHand.MAIN_HAND, itemStack);
            } else {
                player().displayClientMessage(ModTexts.Messages.errorCreativeMode(ModTexts.ITEM), false);
            }
        }
        GuapiScreenHandler.INSTANCE.hideScene();
    }

    private static void updatePlayerInventoryItem(ItemStack itemStack, int slotId, boolean isCreativeInventoryScreen) {
        LOGGER.debug("Updating player inventory item {} in slot {}", itemStack, slotId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendPlayerInventoryItemUpdate(itemStack, slotId);
        } else {
            if (player().isCreative()) {
                if (isCreativeInventoryScreen) {
                    player().inventory.setItem(slotId, itemStack);
                } else {
                    Minecraft.getInstance().gameMode.handleCreativeModeItemAdd(itemStack, slotId);
                }
            } else {
                player().displayClientMessage(ModTexts.Messages.errorServerMod(ModTexts.ITEM), false);
            }
        }
        GuapiScreenHandler.INSTANCE.hideScene();
    }

    private static void updateBlockInventoryItem(ItemStack itemStack, int slotId, BlockPos blockPos) {
        LOGGER.debug("Updating block inventory item {} at pos {} and in slot {}", itemStack, blockPos, slotId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockInventoryItemUpdate(itemStack, slotId, blockPos);
        } else {
            player().displayClientMessage(ModTexts.Messages.errorServerMod(ModTexts.ITEM), false);
        }
        GuapiScreenHandler.INSTANCE.hideScene();
    }

    private static void updateBlock(BlockPos pos, BlockState state, CompoundTag tag) {
        LOGGER.debug("Updating block {} at pos {}", state, pos);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockUpdate(pos, state, tag);
        } else {
            player().displayClientMessage(ModTexts.Messages.errorServerMod(ModTexts.BLOCK), false);
        }
        GuapiScreenHandler.INSTANCE.hideScene();
    }

    private static void updateEntity(int entityId, CompoundTag tag) {
        LOGGER.debug("Updating entity {} with id {}", tag.toString(), entityId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendEntityUpdate(entityId, tag);
        } else {
            player().displayClientMessage(ModTexts.Messages.errorServerMod(ModTexts.ENTITY), false);
        }
        GuapiScreenHandler.INSTANCE.hideScene();
    }

    private static CompoundTag parseTag(String snbt) {
        try {
            return TagParser.parseTag(snbt);
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static LocalPlayer player() {
        return Minecraft.getInstance().player;
    }

    private static MutableComponent getDisabledTooltipServerMod(MutableComponent arg) {
        return ClientContext.isModInstalledOnServer() ? null : ModTexts.serverMod(arg);
    }

    private static MutableComponent getDisabledTooltipCreativeMode(MutableComponent arg) {
        return ClientContext.isModInstalledOnServer() || player().isCreative() ? null : ModTexts.creativeMode(arg);
    }
}
