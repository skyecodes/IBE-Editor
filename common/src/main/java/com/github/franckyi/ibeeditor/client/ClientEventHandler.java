package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.ClientPlatformUtil;
import com.github.franckyi.ibeeditor.common.EditorContext;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onKeyInput() {
        try {
            if (KeyBindings.getEditorKey().consumeClick()) {
                initContextAndRequestWorldEditor(EditorContext.EditorType.STANDARD);
            } else if (KeyBindings.getNBTEditorKey().consumeClick()) {
                initContextAndRequestWorldEditor(EditorContext.EditorType.NBT);
            } else if (KeyBindings.getSNBTEditorKey().consumeClick()) {
                initContextAndRequestWorldEditor(EditorContext.EditorType.SNBT);
            } else if (KeyBindings.getVaultKey().consumeClick()) {
                ModScreenHandler.openVault();
            }
        } catch (Exception e) {
            ClientUtil.showMessage(ModTexts.Messages.ERROR_GENERIC);
            LOGGER.error("Error while handling ingame key input for IBE Editor mod", e);
        }
    }

    private static void initContextAndRequestWorldEditor(EditorContext.EditorType editorType) {
        var ctx = EditorContext.fromKeybind();
        ctx.setTrigger(EditorContext.Trigger.KEYBIND_WORLD);
        ctx.setEditorType(editorType);
        ClientEditorLogic.requestWorldEditor(ctx);
    }

    public static boolean onScreenEvent(AbstractContainerScreen<?> screen, int keyCode) {
        try {
            if (keyCode == ClientPlatformUtil.getKeyCode(KeyBindings.getEditorKey())) {
                return initContextAndRequestInventoryItemEditor(EditorContext.EditorType.STANDARD, screen);
            } else if (keyCode == ClientPlatformUtil.getKeyCode(KeyBindings.getNBTEditorKey())) {
                return initContextAndRequestInventoryItemEditor(EditorContext.EditorType.NBT, screen);
            } else if (keyCode == ClientPlatformUtil.getKeyCode(KeyBindings.getSNBTEditorKey())) {
                return initContextAndRequestInventoryItemEditor(EditorContext.EditorType.SNBT, screen);
            }
        } catch (Exception e) {
            ClientUtil.showMessage(ModTexts.Messages.ERROR_GENERIC);
            LOGGER.error("Error while handling screen key input for IBE Editor mod", e);
        }
        return false;
    }

    private static boolean initContextAndRequestInventoryItemEditor(EditorContext.EditorType editorType, AbstractContainerScreen<?> screen) {
        var ctx = EditorContext.fromKeybind();
        ctx.setTarget(EditorContext.Target.ITEM);
        ctx.setTrigger(EditorContext.Trigger.KEYBIND_INVENTORY);
        ctx.setEditorType(editorType);
        return ClientEditorLogic.requestInventoryItemEditor(ctx, screen);
    }
}
