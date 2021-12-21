package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.ibeeditor.PlatformUtilClient;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import com.github.franckyi.ibeeditor.mixin.AbstractContainerScreenMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onKeyInput() {
        try {
            if (KeyBindings.editorKey.consumeClick()) {
                ClientEditorLogic.openWorldEditor(EditorType.STANDARD);
            } else if (KeyBindings.nbtEditorKey.consumeClick()) {
                ClientEditorLogic.openWorldEditor(EditorType.NBT);
            } else if (KeyBindings.snbtEditorKey.consumeClick()) {
                ClientEditorLogic.openWorldEditor(EditorType.SNBT);
            }/* else if (KeyBindings.vaultKey.isPressed()) {
                ClientEditorLogic.openVault();
            }*/
        } catch (Exception e) {
            Minecraft.getInstance().player.displayClientMessage(ModTexts.Messages.ERROR_GENERIC, false);
            LOGGER.error("Error while handling ingame key input for IBE Editor mod", e);
        }
    }

    public static void onScreenEvent(AbstractContainerScreen<?> screen, int keyCode) {
        try {
            EditorType type = null;
            if (keyCode == PlatformUtilClient.getKeyCode(KeyBindings.editorKey)) {
                type = EditorType.STANDARD;
            } else if (keyCode == PlatformUtilClient.getKeyCode(KeyBindings.nbtEditorKey)) {
                type = EditorType.NBT;
            } else if (keyCode == PlatformUtilClient.getKeyCode(KeyBindings.snbtEditorKey)) {
                type = EditorType.SNBT;
            }
            if (type != null) {
                Slot slot = ((AbstractContainerScreenMixin) screen).getHoveredSlot();
                if (slot != null && slot.hasItem()) {
                    if (slot.container instanceof Inventory) {
                        ClientEditorLogic.openPlayerInventoryItemEditor(slot.getItem(), type, slot.index, screen instanceof CreativeModeInventoryScreen);
                    } else {
                        HitResult hitResult = Minecraft.getInstance().hitResult;
                        if (hitResult instanceof BlockHitResult blockHitResult) {
                            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(blockHitResult.getBlockPos());
                            if (blockEntity instanceof Container) {
                                ClientEditorLogic.openBlockInventoryItemEditor(slot.getItem(), type, slot.index, blockHitResult.getBlockPos());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Minecraft.getInstance().player.displayClientMessage(ModTexts.Messages.ERROR_GENERIC, false);
            LOGGER.error("Error while handling screen key input for IBE Editor mod", e);
        }
    }
}
