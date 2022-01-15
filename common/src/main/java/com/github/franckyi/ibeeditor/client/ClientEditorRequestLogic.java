package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.common.EditorContext;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.mixin.AbstractContainerScreenMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientEditorRequestLogic {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void requestWorldEditor(EditorContext ctx) {
        if (!(requestEntityEditor(ctx) || requestBlockEditor(ctx) || requestMainHandItemEditor(ctx))) {
            requestSelfEditor(ctx);
        }
    }

    public static boolean requestEntityEditor(EditorContext ctx) {
        HitResult result = Minecraft.getInstance().hitResult;
        if (result instanceof EntityHitResult res) {
            Entity entity = res.getEntity();
            ctx.setTarget(EditorContext.Target.ENTITY);
            ctx.setEntityId(entity.getId());
            ctx.setEntity(entity);
            requestEditor(ctx);
            return true;
        }
        return false;
    }

    public static boolean requestBlockEditor(EditorContext ctx) {
        HitResult result = Minecraft.getInstance().hitResult;
        if (result instanceof BlockHitResult res) {
            ClientLevel level = Minecraft.getInstance().level;
            if (!level.isEmptyBlock(res.getBlockPos())) {
                BlockPos blockPos = res.getBlockPos();
                ctx.setTarget(EditorContext.Target.BLOCK);
                ctx.setBlockPos(blockPos);
                ctx.setBlockState(level.getBlockState(blockPos));
                ctx.setBlockEntity(level.getBlockEntity(blockPos));
                requestEditor(ctx);
                return true;
            }
        }
        return false;
    }

    public static boolean requestMainHandItemEditor(EditorContext ctx) {
        ItemStack itemStack = player().getMainHandItem();
        if (itemStack != null && !itemStack.isEmpty()) {
            ctx.setTarget(EditorContext.Target.ITEM);
            ctx.setItemInventory(EditorContext.ItemInventory.MAIN_HAND);
            ctx.setItemStack(itemStack);
            requestEditor(ctx);
            return true;
        }
        return false;
    }

    public static void requestSelfEditor(EditorContext ctx) {
        ctx.setTarget(EditorContext.Target.ENTITY);
        ctx.setEntityId(player().getId());
        ctx.setEntity(player());
        requestEditor(ctx);
    }

    public static boolean requestInventoryItemEditor(EditorContext ctx, AbstractContainerScreen<?> screen) {
        var slot = ((AbstractContainerScreenMixin) screen).getHoveredSlot();
        if (slot != null && slot.hasItem()) {
            int slotIndex = slot.getContainerSlot();
            if (slot.container instanceof Inventory) {
                ctx.setItemInventory(EditorContext.ItemInventory.PLAYER_INVENTORY);
                boolean survivalInventoryTab = false;
                if (screen instanceof CreativeModeInventoryScreen c) {
                    ctx.setCreativeInventoryScreen(true);
                    survivalInventoryTab = c.getSelectedTab() == CreativeModeTab.TAB_INVENTORY.getId();
                }
                if (survivalInventoryTab) {
                    if (slotIndex == 45) {
                        slotIndex = 40;
                    } else if (slotIndex >= 36) {
                        slotIndex %= 36;
                    } else if (slotIndex < 9) {
                        slotIndex = 36 + 8 - slotIndex;
                    }
                }
                ctx.setSlotIndex(slotIndex);
                requestEditor(ctx);
                return true;
            } else {
                HitResult hitResult = Minecraft.getInstance().hitResult;
                if (hitResult instanceof BlockHitResult res) {
                    BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(res.getBlockPos());
                    if (blockEntity instanceof Container) {
                        ctx.setItemInventory(EditorContext.ItemInventory.BLOCK_INVENTORY);
                        ctx.setSlotIndex(slotIndex);
                        ctx.setBlockPos(res.getBlockPos());
                        requestEditor(ctx);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void requestEditor(EditorContext ctx) {
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendEditorRequest(ctx);
        } else {
            ModScreenHandler.openEditor(ctx);
        }
    }

    /*private static void updatePlayerMainHandItem(ItemStack itemStack) {
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
    }

    private static void updateBlockInventoryItem(ItemStack itemStack, int slotId, BlockPos blockPos) {
        LOGGER.debug("Updating block inventory item {} at pos {} and in slot {}", itemStack, blockPos, slotId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockInventoryItemUpdate(itemStack, slotId, blockPos);
        } else {
            player().displayClientMessage(ModTexts.Messages.errorServerMod(ModTexts.ITEM), false);
        }
    }

    private static void updateBlock(BlockPos pos, BlockState state, CompoundTag tag) {
        LOGGER.debug("Updating block {} at pos {}", state, pos);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendBlockUpdate(pos, state, tag);
        } else {
            player().displayClientMessage(ModTexts.Messages.errorServerMod(ModTexts.BLOCK), false);
        }
    }

    private static void updateEntity(int entityId, CompoundTag tag) {
        LOGGER.debug("Updating entity {} with id {}", tag.toString(), entityId);
        if (ClientContext.isModInstalledOnServer()) {
            ClientNetworkEmitter.sendEntityUpdate(entityId, tag);
        } else {
            player().displayClientMessage(ModTexts.Messages.errorServerMod(ModTexts.ENTITY), false);
        }
    }*/


    private static LocalPlayer player() {
        return Minecraft.getInstance().player;
    }

    @Deprecated
    private static MutableComponent getDisabledTooltipServerMod(MutableComponent arg) {
        return ClientContext.isModInstalledOnServer() ? null : ModTexts.serverMod(arg);
    }
}
