package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.ibeeditor.common.EditorType;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
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

    public static void openWorldEditor(EditorType type) {
        LOGGER.debug("Opening world editor with type={}", type);
        if (!(tryOpenEntityEditor(type) || tryOpenBlockEditor(type) || tryOpenItemEditor(type))) {
            tryOpenSelfEditor(type);
        }
    }

    public static boolean tryOpenEntityEditor(EditorType type) {
        LOGGER.debug("Trying to open entity editor with type={}", type);
        HitResult result = Minecraft.getInstance().hitResult;
        if (result instanceof EntityHitResult res) {
            Entity entity = res.getEntity();
            if (ClientContext.isModInstalledOnServer()) {
                requestOpenEntityEditor(entity.getId(), type);
            } else {
                openEntityEditor(entity.saveWithoutId(new CompoundTag()), entity.getId(), type);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor(EditorType type) {
        LOGGER.debug("Trying to open block editor with type={}", type);
        HitResult result = Minecraft.getInstance().hitResult;
        if (result instanceof BlockHitResult res) {
            ClientLevel level = Minecraft.getInstance().level;
            if (!level.isEmptyBlock(res.getBlockPos())) {
                BlockPos pos = res.getBlockPos();
                if (ClientContext.isModInstalledOnServer()) {
                    requestOpenBlockEditor(pos, type);
                } else {
                    BlockState state = level.getBlockState(pos);
                    BlockEntity entity = level.getBlockEntity(pos);
                    CompoundTag tag = entity == null ? null : entity.saveWithoutMetadata();
                    openBlockEditor(pos, state, tag, type);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean tryOpenItemEditor(EditorType type) {
        LOGGER.debug("Trying to item editor with type={}", type);
        ItemStack itemStack = player().getMainHandItem();
        if (itemStack != null && !itemStack.isEmpty()) {
            if (ClientContext.isModInstalledOnServer()) {
                requestOpenMainHandItemEditor(type);
            } else {
                openMainHandItemEditor(itemStack, type);
            }
            return true;
        }
        return false;
    }

    public static void tryOpenSelfEditor(EditorType type) {
        LOGGER.debug("Trying to open self editor with type={}", type);
        if (ClientContext.isModInstalledOnServer()) {
            requestOpenEntityEditor(player().getId(), type);
        } else {
            openEntityEditor(player().saveWithoutId(new CompoundTag()), player().getId(), type);
        }
    }

    public static void openVault() {
        //LOGGER.debug("Opening vault");
        //ModScreenHandler.openVault();
    }

    private static void requestOpenMainHandItemEditor(EditorType type) {
        LOGGER.debug("Requesting main hand item editor with type={}", type);
        ClientNetworkEmitter.sendMainHandItemEditorRequest(type);
    }

    public static void openMainHandItemEditor(ItemStack itemStack, EditorType type) {
        openItemEditor(itemStack, type, ClientEditorLogic::updatePlayerMainHandItem,
                getDisabledTooltipCreativeMode(ModTexts.ITEM));
    }

    public static boolean tryOpenItemEditorFromScreen(EditorType type, Slot slot, boolean isCreativeInventoryScreen, boolean isCreativeSurvivalInventory) {
        if (slot != null && slot.hasItem()) {
            if (slot.container instanceof Inventory) {
                int slotIndex = slot.getContainerSlot();
                if (isCreativeSurvivalInventory) {
                    if (slotIndex == 45) {
                        slotIndex = 40;
                    } else if (slotIndex >= 36) {
                        slotIndex %= 36;
                    } else if (slotIndex < 9) {
                        slotIndex = 36 + 8 - slotIndex;
                    }
                }
                if (ClientContext.isModInstalledOnServer()) {
                    requestOpenPlayerInventoryItemEditor(type, slotIndex, isCreativeInventoryScreen);
                } else {
                    openPlayerInventoryItemEditor(slot.getItem(), type, slotIndex, isCreativeInventoryScreen);
                }
                return true;
            } else {
                HitResult hitResult = Minecraft.getInstance().hitResult;
                if (hitResult instanceof BlockHitResult res) {
                    BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(res.getBlockPos());
                    if (blockEntity instanceof Container) {
                        if (ClientContext.isModInstalledOnServer()) {
                            requestOpenBlockInventoryItemEditor(type, slot.getContainerSlot(), res.getBlockPos());
                        } else {
                            openBlockInventoryItemEditor(slot.getItem(), type, slot.getContainerSlot(), res.getBlockPos());
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void requestOpenPlayerInventoryItemEditor(EditorType type, int slotIndex, boolean isCreativeInventoryScreen) {
        LOGGER.debug("Requesting player inventory item editor at slot index {} with type={}", slotIndex, type);
        ClientNetworkEmitter.sendPlayerInventoryItemEditorRequest(type, slotIndex, isCreativeInventoryScreen);
    }

    public static void openPlayerInventoryItemEditor(ItemStack itemStack, EditorType type, int slotId, boolean isCreativeInventoryScreen) {
        openItemEditor(itemStack, type,
                newItem -> updatePlayerInventoryItem(newItem, slotId, isCreativeInventoryScreen),
                getDisabledTooltipCreativeMode(ModTexts.ITEM));
    }

    private static void requestOpenBlockInventoryItemEditor(EditorType type, int slotIndex, BlockPos pos) {
        LOGGER.debug("Requesting player inventory item editor at pos {} and slot index {} with type={}", pos, slotIndex, type);
        ClientNetworkEmitter.sendBlockInventoryItemEditorRequest(type, slotIndex, pos);
    }

    public static void openBlockInventoryItemEditor(ItemStack itemStack, EditorType type, int slotId, BlockPos blockPos) {
        openItemEditor(itemStack, type,
                newItem -> updateBlockInventoryItem(newItem, slotId, blockPos),
                getDisabledTooltipServerMod(ModTexts.ITEM));
    }

    public static void openItemEditor(ItemStack itemStack, EditorType type, Consumer<ItemStack> action, Component disabledTooltip) {
        LOGGER.debug("Opening item editor for item {} with type={})", itemStack, type);
        switch (type) {
            case STANDARD -> ModScreenHandler.openItemEditorScreen(itemStack, action, disabledTooltip);
            case NBT -> ModScreenHandler.openNBTEditorScreen(itemStack.save(new CompoundTag()),
                    tag -> action.accept(ItemStack.of(tag)), disabledTooltip);
            case SNBT -> ModScreenHandler.openSNBTEditorScreen(itemStack.save(new CompoundTag()).toString(),
                    snbt -> action.accept(ItemStack.of(parseTag(snbt))), disabledTooltip);
        }
    }

    public static void requestOpenBlockEditor(BlockPos pos, EditorType type) {
        LOGGER.debug("Requesting block editor at pos {} with type={}", pos, type);
        ClientNetworkEmitter.sendBlockEditorRequest(pos, type);
    }

    public static void openBlockEditor(BlockPos pos, BlockState state, CompoundTag tag, EditorType type) {
        LOGGER.debug("Opening block editor for block {} at pos {} with type={}", state, pos, type);
        switch (type) {
            case STANDARD -> ModScreenHandler.openBlockEditorScreen(state, tag,
                    (newState, newTag) -> updateBlock(pos, newState, newTag),
                    getDisabledTooltipServerMod(ModTexts.BLOCK));
            case NBT -> {
                if (tag == null) {
                    player().displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.BLOCK), false);
                    break;
                }
                ModScreenHandler.openNBTEditorScreen(tag,
                        newTag -> updateBlock(pos, state, newTag),
                        getDisabledTooltipServerMod(ModTexts.BLOCK));
            }
            case SNBT -> {
                if (tag == null) {
                    player().displayClientMessage(ModTexts.Messages.errorNoTargetFound(ModTexts.BLOCK), false);
                    break;
                }
                ModScreenHandler.openSNBTEditorScreen(tag.toString(),
                        snbt -> updateBlock(pos, state, parseTag(snbt)),
                        getDisabledTooltipServerMod(ModTexts.BLOCK));
            }
        }
    }

    public static void requestOpenEntityEditor(int entityId, EditorType type) {
        LOGGER.debug("Requesting entity editor for entityId {} with type={}", entityId, type);
        ClientNetworkEmitter.sendEntityEditorRequest(entityId, type);
    }

    public static void openEntityEditor(CompoundTag entity, int entityId, EditorType type) {
        LOGGER.debug("Opening entity editor for entity {} with id {} and type={}", entity, entityId, type);
        switch (type) {
            case STANDARD ->
                /*ModScreenHandler.openEntityEditorScreen(entity,
                        entity1 -> updateEntity(entityId, entity1),
                        getDisabledTooltipServerMod(ModTexts.ENTITY));*/
                    player().displayClientMessage(ModTexts.Messages.warnNotImplemented(ModTexts.ENTITY), false);
            case NBT -> ModScreenHandler.openNBTEditorScreen(entity,
                    tag -> updateEntity(entityId, tag),
                    getDisabledTooltipServerMod(ModTexts.ENTITY));
            case SNBT -> ModScreenHandler.openSNBTEditorScreen(entity.toString(),
                    snbt -> updateEntity(entityId, parseTag(snbt)),
                    getDisabledTooltipServerMod(ModTexts.ENTITY));
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
        Guapi.getScreenHandler().hideScene();
    }

    private static void updatePlayerInventoryItem(ItemStack itemStack, int slotId, boolean isCreativeInventoryScreen) {
        LOGGER.debug("Updating player inventory item {} in slot {}", itemStack, slotId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendPlayerInventoryItemUpdate(itemStack, slotId);
        } else {
            if (player().isCreative()) {
                if (isCreativeInventoryScreen) {
                    player().getInventory().setItem(slotId, itemStack);
                } else {
                    Minecraft.getInstance().gameMode.handleCreativeModeItemAdd(itemStack, slotId);
                }
            } else {
                player().displayClientMessage(ModTexts.Messages.errorServerMod(ModTexts.ITEM), false);
            }
        }
        Guapi.getScreenHandler().hideScene();
    }

    private static void updateBlockInventoryItem(ItemStack itemStack, int slotId, BlockPos blockPos) {
        LOGGER.debug("Updating block inventory item {} at pos {} and in slot {}", itemStack, blockPos, slotId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockInventoryItemUpdate(itemStack, slotId, blockPos);
        } else {
            player().displayClientMessage(ModTexts.Messages.errorServerMod(ModTexts.ITEM), false);
        }
        Guapi.getScreenHandler().hideScene();
    }

    private static void updateBlock(BlockPos pos, BlockState state, CompoundTag tag) {
        LOGGER.debug("Updating block {} at pos {}", state, pos);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockUpdate(pos, state, tag);
        } else {
            player().displayClientMessage(ModTexts.Messages.errorServerMod(ModTexts.BLOCK), false);
        }
        Guapi.getScreenHandler().hideScene();
    }

    private static void updateEntity(int entityId, CompoundTag tag) {
        LOGGER.debug("Updating entity {} with id {}", tag.toString(), entityId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendEntityUpdate(entityId, tag);
        } else {
            player().displayClientMessage(ModTexts.Messages.errorServerMod(ModTexts.ENTITY), false);
        }
        Guapi.getScreenHandler().hideScene();
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
