package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.ibeeditor.impl.common.EditorType;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.client.screen.Screen;
import com.github.franckyi.minecraft.api.common.Slot;
import com.github.franckyi.minecraft.api.common.world.WorldBlock;

public final class ClientEventHandler {
    public static void onKeyInput() {
        if (KeyBindings.editorKey.isPressed()) {
            ClientEditorLogic.openWorldEditor(EditorType.STANDARD);
        } else if (KeyBindings.nbtEditorKey.isPressed()) {
            ClientEditorLogic.openWorldEditor(EditorType.NBT);
        } else if (KeyBindings.rawNbtEditorKey.isPressed()) {
            ClientEditorLogic.openWorldEditor(EditorType.RAW_NBT);
        } else if (KeyBindings.clipboardKey.isPressed()) {
            ClientEditorLogic.openClipboard();
        }
    }

    public static void onScreenEvent(Screen screen, int keyCode) {
        EditorType type = null;
        if (keyCode == KeyBindings.editorKey.getKeyCode()) {
            type = EditorType.STANDARD;
        } else if (keyCode == KeyBindings.nbtEditorKey.getKeyCode()) {
            type = EditorType.NBT;
        } else if (keyCode == KeyBindings.rawNbtEditorKey.getKeyCode()) {
            type = EditorType.RAW_NBT;
        }
        if (type != null) {
            Slot slot = screen.getInventoryFocusedSlot();
            if (slot.hasStack()) {
                if (slot.isInPlayerInventory()) {
                    ClientEditorLogic.openPlayerInventoryItemEditor(slot.getStack(), type, slot.getIndex(), screen.isCreativeInventoryScreen());
                } else {
                    WorldBlock block = Minecraft.getClient().getBlockMouseOver();
                    if (block != null) {
                        ClientEditorLogic.openBlockInventoryItemEditor(slot.getStack(), type, slot.getIndex(), block.getBlockPos());
                    }
                }
            }
        }
    }
}
