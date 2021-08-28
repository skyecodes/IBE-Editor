package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.client.IScreen;
import com.github.franckyi.gameadapter.api.common.item.ISlot;
import com.github.franckyi.gameadapter.api.common.world.WorldBlockData;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onKeyInput() {
        try {
            if (KeyBindings.editorKey.wasPressed()) {
                ClientEditorLogic.openWorldEditor(EditorType.STANDARD);
            } else if (KeyBindings.nbtEditorKey.wasPressed()) {
                ClientEditorLogic.openWorldEditor(EditorType.NBT);
            } else if (KeyBindings.snbtEditorKey.wasPressed()) {
                ClientEditorLogic.openWorldEditor(EditorType.SNBT);
            }/* else if (KeyBindings.clipboardKey.isPressed()) {
            ClientEditorLogic.openClipboard();
            }*/
        } catch (Exception e) {
            LOGGER.error("Error while handling ingame key input for IBE Editor mod", e);
        }

    }

    public static void onScreenEvent(IScreen screen, int keyCode) {
        try {
            EditorType type = null;
            if (keyCode == KeyBindings.editorKey.getKeyCode()) {
                type = EditorType.STANDARD;
            } else if (keyCode == KeyBindings.nbtEditorKey.getKeyCode()) {
                type = EditorType.NBT;
            } else if (keyCode == KeyBindings.snbtEditorKey.getKeyCode()) {
                type = EditorType.SNBT;
            }
            if (type != null) {
                ISlot slot = screen.getInventoryFocusedSlot();
                if (slot != null && slot.hasStack()) {
                    if (slot.isInPlayerInventory()) {
                        ClientEditorLogic.openPlayerInventoryItemEditor(slot.getStack(), type, slot.getIndex(), screen.isCreativeInventoryScreen());
                    } else {
                        WorldBlockData block = Game.getClient().getBlockMouseOver();
                        if (block != null) {
                            ClientEditorLogic.openBlockInventoryItemEditor(slot.getStack(), type, slot.getIndex(), block.getPos());
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error while handling screen key input for IBE Editor mod", e);
        }
    }
}
