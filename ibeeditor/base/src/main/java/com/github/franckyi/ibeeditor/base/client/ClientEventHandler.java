package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.client.IScreen;
import com.github.franckyi.gameadapter.api.common.ISlot;
import com.github.franckyi.gameadapter.api.common.WorldBlockData;
import com.github.franckyi.ibeeditor.base.common.EditorType;

public final class ClientEventHandler {
    public static void onKeyInput() {
        if (KeyBindings.editorKey.wasPressed()) {
            ClientEditorLogic.openWorldEditor(EditorType.STANDARD);
        } else if (KeyBindings.nbtEditorKey.wasPressed()) {
            ClientEditorLogic.openWorldEditor(EditorType.NBT);
        } else if (KeyBindings.snbtEditorKey.wasPressed()) {
            ClientEditorLogic.openWorldEditor(EditorType.SNBT);
        }/* else if (KeyBindings.clipboardKey.isPressed()) {
            ClientEditorLogic.openClipboard();
        }*/
    }

    public static void onScreenEvent(IScreen screen, int keyCode) {
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
            if (slot.hasStack()) {
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
    }
}
