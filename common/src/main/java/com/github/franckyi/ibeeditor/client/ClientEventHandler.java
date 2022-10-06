package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.ClientPlatformUtil;
import com.github.franckyi.ibeeditor.client.logic.ClientEditorRequestLogic;
import com.github.franckyi.ibeeditor.common.EditorType;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onKeyInput() {
        try {
            if (KeyBindings.getEditorKey().consumeClick()) {
                ClientEditorRequestLogic.requestWorldEditor(EditorType.STANDARD);
            } else if (KeyBindings.getNBTEditorKey().consumeClick()) {
                ClientEditorRequestLogic.requestWorldEditor(EditorType.NBT);
            } else if (KeyBindings.getSNBTEditorKey().consumeClick()) {
                ClientEditorRequestLogic.requestWorldEditor(EditorType.SNBT);
            } else if (KeyBindings.getVaultKey().consumeClick()) {
                ModScreenHandler.openVault();
            }
        } catch (Exception e) {
            ClientUtil.showMessage(ModTexts.Messages.ERROR_GENERIC);
            LOGGER.error("Error while handling ingame key input for IBE Editor mod", e);
        }
    }

    public static boolean onScreenEvent(AbstractContainerScreen<?> screen, int keyCode) {
        try {
            if (keyCode == ClientPlatformUtil.getKeyCode(KeyBindings.getEditorKey())) {
                return ClientEditorRequestLogic.requestInventoryItemEditor(EditorType.STANDARD, screen);
            } else if (keyCode == ClientPlatformUtil.getKeyCode(KeyBindings.getNBTEditorKey())) {
                return ClientEditorRequestLogic.requestInventoryItemEditor(EditorType.NBT, screen);
            } else if (keyCode == ClientPlatformUtil.getKeyCode(KeyBindings.getSNBTEditorKey())) {
                return ClientEditorRequestLogic.requestInventoryItemEditor(EditorType.SNBT, screen);
            } else if (keyCode == ClientPlatformUtil.getKeyCode(KeyBindings.getVaultKey())) {
                ModScreenHandler.openVault(); // TODO open item vault selection screen
                return true;
            }
        } catch (Exception e) {
            ClientUtil.showMessage(ModTexts.Messages.ERROR_GENERIC);
            LOGGER.error("Error while handling screen key input for IBE Editor mod", e);
        }
        return false;
    }
}
