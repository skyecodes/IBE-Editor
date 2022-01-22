package com.github.franckyi.ibeeditor.client.logic;

import com.github.franckyi.ibeeditor.client.ClientContext;
import com.github.franckyi.ibeeditor.client.ClientUtil;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.context.EntityEditorContext;
import com.github.franckyi.ibeeditor.client.context.ItemEditorContext;
import com.github.franckyi.ibeeditor.common.EditorType;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.common.network.*;
import com.github.franckyi.ibeeditor.mixin.AbstractContainerScreenMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public final class ClientEditorRequestLogic {
    public static void requestWorldEditor(EditorType editorType) {
        if (!(requestEntityEditor(editorType) || requestBlockEditor(editorType) || requestMainHandItemEditor(editorType))) {
            requestSelfEditor(editorType);
        }
    }

    public static boolean requestEntityEditor(EditorType editorType) {
        if (Minecraft.getInstance().hitResult instanceof EntityHitResult res) {
            var entity = res.getEntity();
            if (ClientContext.isModInstalledOnServer()) {
                NetworkManager.sendToServer(NetworkManager.ENTITY_EDITOR_REQUEST, new EntityEditorPacket.Request(editorType, entity.getId()));
            } else {
                var tag = new CompoundTag();
                entity.save(tag);
                ModScreenHandler.openEditor(editorType, new EntityEditorContext(tag, ModTexts.errorServerModRequired(ModTexts.ENTITY), true, null));
            }
            return true;
        }
        return false;
    }

    public static boolean requestBlockEditor(EditorType editorType) {
        if (Minecraft.getInstance().hitResult instanceof BlockHitResult res && res.getType() != HitResult.Type.MISS) {
            var blockPos = res.getBlockPos();
            if (ClientContext.isModInstalledOnServer()) {
                NetworkManager.sendToServer(NetworkManager.BLOCK_EDITOR_REQUEST, new BlockEditorPacket.Request(editorType, blockPos));
            } else {
                ClientUtil.showMessage(ModTexts.Messages.errorServerModRequired(ModTexts.BLOCK));
            }
            return true;
        }
        return false;
    }

    public static boolean requestMainHandItemEditor(EditorType editorType) {
        var item = Minecraft.getInstance().player.getMainHandItem();
        if (item.isEmpty()) {
            return false;
        }
        if (ClientContext.isModInstalledOnServer()) {
            NetworkManager.sendToServer(NetworkManager.MAIN_HAND_ITEM_EDITOR_REQUEST, new MainHandItemEditorPacket.Request(editorType));
        } else {
            if (Minecraft.getInstance().player.isCreative()) {
                ModScreenHandler.openEditor(editorType, new ItemEditorContext(item, null, true, context ->
                        Minecraft.getInstance().player.connection.send(new ServerboundSetCreativeModeSlotPacket(Minecraft.getInstance().player.getInventory().selected + Inventory.INVENTORY_SIZE, context.getItemStack()))));
            } else {
                ModScreenHandler.openEditor(editorType, new ItemEditorContext(item, ModTexts.errorServerModRequired(ModTexts.ITEM), true, null));
            }
        }
        return true;
    }

    public static void requestSelfEditor(EditorType editorType) {
        var entity = Minecraft.getInstance().player;
        if (ClientContext.isModInstalledOnServer()) {
            NetworkManager.sendToServer(NetworkManager.ENTITY_EDITOR_REQUEST, new EntityEditorPacket.Request(editorType, entity.getId()));
        }
    }

    public static boolean requestInventoryItemEditor(EditorType editorType, AbstractContainerScreen<?> screen) {
        var slot = ((AbstractContainerScreenMixin) screen).getHoveredSlot();
        if (slot != null && slot.hasItem()) {
            int slotIndex = slot.getContainerSlot();
            if (slot.container instanceof Inventory) {
                boolean creativeInventoryScreen = false;
                if (screen instanceof CreativeModeInventoryScreen c) {
                    creativeInventoryScreen = true;
                    // "survival inventory" creative tab slot indexes are different, need a hacky fix to get the actual inventory slot id
                    if (c.getSelectedTab() == CreativeModeTab.TAB_INVENTORY.getId()) {
                        if (slotIndex == 45) {
                            slotIndex = 40;
                        } else if (slotIndex >= 36) {
                            slotIndex %= 36;
                        } else if (slotIndex < 9) {
                            slotIndex = 36 + 8 - slotIndex;
                        }
                    }
                }
                if (ClientContext.isModInstalledOnServer()) {
                    NetworkManager.sendToServer(NetworkManager.PLAYER_INVENTORY_ITEM_EDITOR_REQUEST, new PlayerInventoryItemEditorPacket.Request(editorType, slotIndex, creativeInventoryScreen));
                } else {
                    if (Minecraft.getInstance().player.isCreative()) {
                        ModScreenHandler.openEditor(editorType, new ItemEditorContext(slot.getItem(), null, true, context -> slot.set(context.getItemStack())));
                    } else {
                        ModScreenHandler.openEditor(editorType, new ItemEditorContext(slot.getItem(), ModTexts.errorServerModRequired(ModTexts.ITEM), true, null));
                    }
                }
                return true;
            } else if (Minecraft.getInstance().hitResult instanceof BlockHitResult res && Minecraft.getInstance().level.getBlockEntity(res.getBlockPos()) instanceof Container) {
                if (ClientContext.isModInstalledOnServer()) {
                    NetworkManager.sendToServer(NetworkManager.BLOCK_INVENTORY_ITEM_EDITOR_REQUEST, new BlockInventoryItemEditorPacket.Request(editorType, slotIndex, res.getBlockPos()));
                }
                return true;
            } else if (Minecraft.getInstance().hitResult instanceof EntityHitResult res && res.getEntity() instanceof Container) {
                if (ClientContext.isModInstalledOnServer()) {
                    NetworkManager.sendToServer(NetworkManager.ENTITY_INVENTORY_ITEM_EDITOR_REQUEST, new EntityInventoryItemEditorPacket.Request(editorType, slotIndex, res.getEntity().getId()));
                }
            }
        }
        return false;
    }
}
