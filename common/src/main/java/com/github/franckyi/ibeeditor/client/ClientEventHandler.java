package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.ClientPlatformUtil;
import com.github.franckyi.ibeeditor.common.EditorType;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.mixin.AbstractContainerScreenMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onKeyInput() {
        try {
            if (KeyBindings.getEditorKey().consumeClick()) {
                ClientEditorLogic.openWorldEditor(EditorType.STANDARD);
            } else if (KeyBindings.getNBTEditorKey().consumeClick()) {
                ClientEditorLogic.openWorldEditor(EditorType.NBT);
            } else if (KeyBindings.getSNBTEditorKey().consumeClick()) {
                ClientEditorLogic.openWorldEditor(EditorType.SNBT);
            } else if (KeyBindings.getVaultKey().consumeClick()) {
                ClientEditorLogic.openVault();
            }
        } catch (Exception e) {
            Minecraft.getInstance().player.displayClientMessage(ModTexts.Messages.ERROR_GENERIC, false);
            LOGGER.error("Error while handling ingame key input for IBE Editor mod", e);
        }
    }

    public static boolean onScreenEvent(AbstractContainerScreen<?> screen, int keyCode) {
        try {
            EditorType type = null;
            if (keyCode == ClientPlatformUtil.getKeyCode(KeyBindings.getEditorKey())) {
                type = EditorType.STANDARD;
            } else if (keyCode == ClientPlatformUtil.getKeyCode(KeyBindings.getNBTEditorKey())) {
                type = EditorType.NBT;
            } else if (keyCode == ClientPlatformUtil.getKeyCode(KeyBindings.getSNBTEditorKey())) {
                type = EditorType.SNBT;
            }
            if (type != null) {
                return ClientEditorLogic.tryOpenItemEditorFromScreen(type, ((AbstractContainerScreenMixin) screen).getHoveredSlot(), screen instanceof CreativeModeInventoryScreen,
                        screen instanceof CreativeModeInventoryScreen c && c.getSelectedTab() == CreativeModeTab.TAB_INVENTORY.getId());
            }
        } catch (Exception e) {
            Minecraft.getInstance().player.displayClientMessage(ModTexts.Messages.ERROR_GENERIC, false);
            LOGGER.error("Error while handling screen key input for IBE Editor mod", e);
        }
        return false;
    }
}
